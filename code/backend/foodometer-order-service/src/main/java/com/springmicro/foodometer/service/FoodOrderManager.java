package com.springmicro.foodometer.service;

import com.springmicro.foodometer.constants.FoodOrderConstants;
import com.springmicro.foodometer.constants.FoodOrderEvent;
import com.springmicro.foodometer.constants.FoodOrderStatus;
import com.springmicro.foodometer.constants.UserRole;
import com.springmicro.foodometer.document.FoodOrder;
import com.springmicro.foodometer.document.FoodOrderPreparation;
import com.springmicro.foodometer.exception.OrderException;
import com.springmicro.foodometer.repository.FoodOrderPreparationRepository;
import com.springmicro.foodometer.repository.FoodOrderRepository;
import com.springmicro.foodometer.statemachine.FoodOrderStateChangeInterceptor;
import com.springmicro.foodometer.web.dto.StaffDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.access.StateMachineAccess;
import org.springframework.statemachine.access.StateMachineFunction;
import org.springframework.statemachine.config.StateMachineFactory;
import org.springframework.statemachine.support.DefaultStateMachineContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class FoodOrderManager {
    private final StateMachineFactory<FoodOrderStatus, FoodOrderEvent> stateMachineFactory;
    private final FoodOrderRepository foodOrderRepository;
    private final FoodOrderStateChangeInterceptor foodOrderStateChangeInterceptor;
    private final FoodOrderPreparationRepository foodOrderPreparationRepository;
    private final UserLookUpService userLookUpService;

    @Transactional
    public void newFoodOrder(FoodOrder foodOrder) {
        log.info("Sending new order request to state machine");
        foodOrder.setOrderStatus(FoodOrderStatus.NEW);
        FoodOrder newFoodOrder = foodOrderRepository.save(foodOrder);
        sendFoodOrderEvent(newFoodOrder, FoodOrderEvent.CONFIRM_ORDER);
        awaitForStatus(foodOrder.getId(), FoodOrderStatus.PLACED);
    }

    @Transactional
    public FoodOrder cancelFoodOrder(FoodOrder foodOrder) {
        log.info("Sending cancel order request to state machine");
        FoodOrderStatus currentOrderStatus = foodOrder.getOrderStatus();
        foodOrder.setOrderStatus(FoodOrderStatus.CANCELLED);
        if(sendFoodOrderEvent(foodOrder, FoodOrderEvent.CANCEL_ORDER)) {
            FoodOrder cancelledFoodOrder = foodOrderRepository.save(foodOrder);
            awaitForStatus(foodOrder.getId(), FoodOrderStatus.CANCELLED);
            return cancelledFoodOrder;
        } else {
            String errorMessage = "Order cancellation request could not be processed for ID - " + foodOrder.getId() + ". Current Order status - " + currentOrderStatus;
            log.error(errorMessage);
            throw new OrderException(errorMessage);
        }
    }

    @Transactional
    public FoodOrder allocateFoodOrder(FoodOrder foodOrder) {
        while(true) {
            List<StaffDto> staffDtos = userLookUpService.fetchStaffsByRole(UserRole.CHEF);
            log.info("chefs - "+staffDtos);
            List<String> chefIdsOccupiedForPreparingOrders = foodOrderPreparationRepository.findByFoodOrderStatus(FoodOrderStatus.PREPARING)
                    .stream()
                    .map(foodOrderPreparation -> foodOrderPreparation.getStaffId())
                    .collect(Collectors.toList());
            log.info("occupied chef ids - "+chefIdsOccupiedForPreparingOrders);
            Optional<StaffDto> allocatedChefDtoOptional = staffDtos.stream()
                    .filter(staffDto -> !chefIdsOccupiedForPreparingOrders.contains(staffDto.getId()))
                    .findAny();

            if (allocatedChefDtoOptional.isPresent()) {
                StaffDto allocatedChefDto = allocatedChefDtoOptional.get();
                foodOrderPreparationRepository.save(FoodOrderPreparation.builder()
                        .foodOrderId(foodOrder.getId())
                        .staffId(allocatedChefDto.getId())
                        .foodOrderStatus(FoodOrderStatus.PREPARING)
                        .build());
                log.info("Food Order " + foodOrder.getId() + " allocated to chef " + allocatedChefDto);

                log.info("Sending prepare order request to state machine");
                foodOrder.setOrderStatus(FoodOrderStatus.PREPARING);
                FoodOrder preparingFoodOrder = foodOrderRepository.save(foodOrder);
                sendFoodOrderEvent(preparingFoodOrder, FoodOrderEvent.PREPARE);
                awaitForStatus(foodOrder.getId(), FoodOrderStatus.PREPARING);

                return preparingFoodOrder;
            } else {
                log.error("No chef is available to prepare order " + foodOrder.getId() + ". Checking for chef availability again after 1 min.");
                try {
                    Thread.sleep(1 * 60 * 1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Transactional
    public FoodOrder foodOrderPreparationComplete(FoodOrder foodOrder) {
        try {
            log.info("Please wait till your food is being prepared for order id "+foodOrder.getId());
            long sleepTime = new Random().ints(1, 5).limit(1).sum();
            log.info("Waiting for " + sleepTime + " minute(s)..");
            Thread.sleep(sleepTime * 60 * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        log.info("Sending preparation complete request to state machine");
        foodOrder.setOrderStatus(FoodOrderStatus.PREPARED);
        FoodOrder preparedFoodOrder = foodOrderRepository.save(foodOrder);
        sendFoodOrderEvent(preparedFoodOrder, FoodOrderEvent.PREPARATION_COMPLETE);
        awaitForStatus(foodOrder.getId(), FoodOrderStatus.PREPARED);

        FoodOrderPreparation foodOrderPreparation = foodOrderPreparationRepository.findByFoodOrderId(foodOrder.getId());
        foodOrderPreparation.setFoodOrderStatus(FoodOrderStatus.PREPARED);
        foodOrderPreparationRepository.save(foodOrderPreparation);

        return preparedFoodOrder;
    }

    private boolean sendFoodOrderEvent(FoodOrder foodOrder, FoodOrderEvent foodOrderEvent) {
        log.info("Sending order request to state machine for event "+foodOrderEvent+" - "+foodOrder);
        StateMachine<FoodOrderStatus, FoodOrderEvent> stateMachine = build(foodOrder);
        Message message = MessageBuilder.withPayload(foodOrderEvent)
                .setHeader(FoodOrderConstants.ORDER_ID_HEADER, foodOrder.getId())
                .build();
        boolean status = stateMachine.sendEvent(message);
        log.info("Result for event transition "+foodOrderEvent+" for ID - "+foodOrder.getId()+" :: "+ status);
        return status;
    }

    private boolean awaitForStatus(String foodOrderId, FoodOrderStatus foodOrderStatus) {
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
                    Thread.sleep(200);
                } catch (Exception e) {
                    // do nothing
                }
            }
        }
        return found.get();
    }

    private StateMachine<FoodOrderStatus, FoodOrderEvent> build(FoodOrder foodOrder){
        StateMachine<FoodOrderStatus, FoodOrderEvent> stateMachine = stateMachineFactory.getStateMachine(foodOrder.getId());
        stateMachine.stop();
        StateMachineFunction<StateMachineAccess<FoodOrderStatus, FoodOrderEvent>> stateMachineFunction = stateMachineAccess -> {
            stateMachineAccess.addStateMachineInterceptor(foodOrderStateChangeInterceptor);
            stateMachineAccess.resetStateMachine(new DefaultStateMachineContext<>(foodOrder.getOrderStatus(),null, null, null));
        };
        stateMachine.getStateMachineAccessor().doWithAllRegions(stateMachineFunction);
        stateMachine.getExtendedState().getVariables().put(FoodOrderConstants.ORDER_OBJECT_HEADER, foodOrder);
        stateMachine.getExtendedState().getVariables().put(FoodOrderConstants.ORDER_ID_HEADER, foodOrder.getId());
        stateMachine.start();
        return stateMachine;
    }
}
