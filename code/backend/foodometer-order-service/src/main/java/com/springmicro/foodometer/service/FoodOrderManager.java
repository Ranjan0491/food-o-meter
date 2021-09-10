package com.springmicro.foodometer.service;

import com.springmicro.foodometer.constants.FoodOrderConstants;
import com.springmicro.foodometer.constants.FoodOrderEvent;
import com.springmicro.foodometer.constants.FoodOrderStatus;
import com.springmicro.foodometer.document.FoodOrder;
import com.springmicro.foodometer.statemachine.FoodOrderStateChangeInterceptor;
import com.springmicro.foodometer.web.dto.FoodOrderDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.config.StateMachineFactory;
import org.springframework.statemachine.support.DefaultStateMachineContext;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

@Service
@RequiredArgsConstructor
@Slf4j
public class FoodOrderManager {
    private final StateMachineFactory<FoodOrderStatus, FoodOrderEvent> stateMachineFactory;
    private final FoodOrderService foodOrderService;
    private final FoodOrderStateChangeInterceptor foodOrderStateChangeInterceptor;

    public void processValidationResult(String foodOrderId, boolean isValid) {
        log.debug("Process Validation Result for foodOrderId: " + foodOrderId + " Valid? " + isValid);
        FoodOrderDto foodOrderDto = foodOrderService.getOrderByOrderId(foodOrderId);
        if(foodOrderDto != null) {
            if(isValid){
                if(foodOrderDto.getOrderStatus() == FoodOrderStatus.NEW) {
                    sendBeerOrderEvent(foodOrderDto, FoodOrderEvent.PAY);
//
//                    //wait for status change
//                    awaitForStatus(foodOrderId, FoodOrderStatus.PAYMENT_PROCESSING);
//
//                    FoodOrderDto validatedFoodOrderDto = foodOrderService.getOrderByOrderId(foodOrderId);
//                    sendBeerOrderEvent(validatedFoodOrderDto, FoodOrderEvent.PAID);
                } else if(foodOrderDto.getOrderStatus() == FoodOrderStatus.PAYMENT_PROCESSING) {
                    sendBeerOrderEvent(foodOrderDto, FoodOrderEvent.PAID);
                }
            } else {
                sendBeerOrderEvent(foodOrderDto, FoodOrderEvent.CANCEL_ORDER);
            }
        } else {
            log.error("Order Not Found. Id: " + foodOrderId);
        }
    }

    private void sendBeerOrderEvent(FoodOrderDto foodOrderDto, FoodOrderEvent foodOrderEvent){
        StateMachine<FoodOrderStatus, FoodOrderEvent> sm = build(foodOrderDto);
        Message msg = MessageBuilder.withPayload(foodOrderEvent)
                .setHeader(FoodOrderConstants.ORDER_ID_HEADER, foodOrderDto.getId())
                .build();
        sm.sendEvent(Mono.just(msg));
    }

    private void awaitForStatus(String foodOrderId, FoodOrderStatus foodOrderStatus) {
        AtomicBoolean found = new AtomicBoolean(false);
        AtomicInteger loopCount = new AtomicInteger(0);
        while (!found.get()) {
            if (loopCount.incrementAndGet() > 10) {
                found.set(true);
                log.debug("Loop retries exceeded. Max retry count is 10");
            }
            FoodOrderDto foodOrderDto = foodOrderService.getOrderByOrderId(foodOrderId);
            if(foodOrderDto != null && foodOrderDto.getOrderStatus().equals(foodOrderStatus)) {
                found.set(true);
                log.debug("Food Order Found");
            } else {
                log.debug("Order Status Not Equal. Expected: " + foodOrderStatus.name() + " Found: " + foodOrderDto.getOrderStatus().name());
            }
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

    private StateMachine<FoodOrderStatus, FoodOrderEvent> build(FoodOrderDto foodOrderDto){
        StateMachine<FoodOrderStatus, FoodOrderEvent> sm = stateMachineFactory.getStateMachine(foodOrderDto.getId());
        sm.stopReactively();
        sm.getStateMachineAccessor()
                .doWithAllRegions(sma -> {
                    sma.addStateMachineInterceptor(foodOrderStateChangeInterceptor);
                    sma.resetStateMachineReactively(
                            new DefaultStateMachineContext<>(foodOrderDto.getOrderStatus(),
                                    null, null, null));
                });
        sm.startReactively();
        return sm;
    }
}
