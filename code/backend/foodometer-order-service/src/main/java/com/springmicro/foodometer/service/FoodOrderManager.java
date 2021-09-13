package com.springmicro.foodometer.service;

import com.springmicro.foodometer.constants.FoodOrderConstants;
import com.springmicro.foodometer.constants.FoodOrderEvent;
import com.springmicro.foodometer.constants.FoodOrderStatus;
import com.springmicro.foodometer.document.FoodOrder;
import com.springmicro.foodometer.repository.FoodOrderRepository;
import com.springmicro.foodometer.statemachine.FoodOrderStateChangeInterceptor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.config.StateMachineFactory;
import org.springframework.statemachine.support.DefaultStateMachineContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

@Service
@RequiredArgsConstructor
@Slf4j
public class FoodOrderManager {
    private final StateMachineFactory<FoodOrderStatus, FoodOrderEvent> stateMachineFactory;
    private final FoodOrderRepository foodOrderRepository;
    private final FoodOrderStateChangeInterceptor foodOrderStateChangeInterceptor;

    @Transactional
    public void newFoodOrder(FoodOrder foodOrder) {
        log.info("Sending new order request to state machine");
        foodOrder.setOrderStatus(FoodOrderStatus.NEW);
        sendFoodOrderEvent(foodOrderRepository.save(foodOrder), FoodOrderEvent.VALIDATE_ORDER);
//        awaitForStatus(foodOrder.getId(), FoodOrderStatus.VALIDATION_PENDING);
    }

    @Transactional
    public void processValidationResult(String foodOrderId, boolean isValid) {
        log.info("Process Validation Result for foodOrderId: " + foodOrderId + " Valid? " + isValid);
        foodOrderRepository.findById(foodOrderId).ifPresentOrElse(foodOrder -> {
            if(isValid){
                if(foodOrder.getOrderStatus() == FoodOrderStatus.NEW) {
                    sendFoodOrderEvent(foodOrder, FoodOrderEvent.VALIDATE_ORDER);

                      // wait for status change
//                    awaitForStatus(foodOrderId, FoodOrderStatus.PAYMENT_PROCESSING);
//
//                    FoodOrderDto validatedFoodOrderDto = foodOrderService.getOrderByOrderId(foodOrderId);
//                    sendFoodOrderEvent(validatedFoodOrderDto, FoodOrderEvent.PAID);
                } else if(foodOrder.getOrderStatus() == FoodOrderStatus.VALIDATION_PENDING) {
                    sendFoodOrderEvent(foodOrder, FoodOrderEvent.VALIDATED);
                    awaitForStatus(foodOrderId, FoodOrderStatus.PLACED);
                }
            } else {
                sendFoodOrderEvent(foodOrder, FoodOrderEvent.CANCEL_ORDER);
            }
        }, () -> log.error("Order Not Found. Id: " + foodOrderId));
    }

    private void sendFoodOrderEvent(FoodOrder foodOrder, FoodOrderEvent foodOrderEvent) {
        log.info("Sending order request to state machine for event "+foodOrderEvent+" - "+foodOrder);
        StateMachine<FoodOrderStatus, FoodOrderEvent> sm = build(foodOrder);
        Message msg = MessageBuilder.withPayload(foodOrderEvent)
                .setHeader(FoodOrderConstants.ORDER_ID_HEADER, foodOrder.getId())
                .build();
        sm.sendEvent(Mono.just(msg));
    }

    private void awaitForStatus(String foodOrderId, FoodOrderStatus foodOrderStatus) {
        AtomicBoolean found = new AtomicBoolean(false);
        AtomicInteger loopCount = new AtomicInteger(0);
        while (!found.get()) {
            if (loopCount.incrementAndGet() > 10) {
                found.set(true);
                log.info("Loop retries exceeded. Max retry count is 10");
            }
            foodOrderRepository.findById(foodOrderId).ifPresentOrElse(foodOrder -> {
                if(foodOrder.getOrderStatus().equals(foodOrderStatus)) {
                    found.set(true);
                    log.info("Food Order Found for id " + foodOrderId);
                }
            }, () -> log.error("Food order not found for id " + foodOrderId + ". Expected status: " + foodOrderStatus.name()));

            if (!found.get()) {
                try {
                    log.debug("Sleeping for retry");
                    Thread.sleep(100);
                } catch (Exception e) {
                    // do nothing
                }
            }
        }
    }

    private StateMachine<FoodOrderStatus, FoodOrderEvent> build(FoodOrder foodOrder){
        StateMachine<FoodOrderStatus, FoodOrderEvent> sm = stateMachineFactory.getStateMachine(foodOrder.getId());
        sm.stopReactively();
        sm.getStateMachineAccessor()
                .doWithAllRegions(sma -> {
                    sma.addStateMachineInterceptor(foodOrderStateChangeInterceptor);
                    sma.resetStateMachineReactively(
                            new DefaultStateMachineContext<>(foodOrder.getOrderStatus(),
                                    null, null, null));
                });
        sm.getExtendedState().getVariables().put(FoodOrderConstants.ORDER_OBJECT_HEADER, foodOrder);
        sm.getExtendedState().getVariables().put(FoodOrderConstants.ORDER_ID_HEADER, foodOrder.getId());
        sm.startReactively();
        return sm;
    }
}
