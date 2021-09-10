package com.springmicro.foodometer.statemachine;

import com.springmicro.foodometer.constants.FoodOrderConstants;
import com.springmicro.foodometer.constants.FoodOrderEvent;
import com.springmicro.foodometer.constants.FoodOrderStatus;
import com.springmicro.foodometer.document.FoodOrder;
import com.springmicro.foodometer.repository.FoodOrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.Message;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.state.State;
import org.springframework.statemachine.support.StateMachineInterceptorAdapter;
import org.springframework.statemachine.transition.Transition;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Slf4j
@Component
@RequiredArgsConstructor
public class FoodOrderStateChangeInterceptor extends StateMachineInterceptorAdapter<FoodOrderStatus, FoodOrderEvent> {

    private final FoodOrderRepository foodOrderRepository;

    @Transactional
    public void preStateChange(State<FoodOrderStatus, FoodOrderEvent> state,
                               Message<FoodOrderEvent> message,
                               Transition<FoodOrderStatus, FoodOrderEvent> transition,
                               StateMachine<FoodOrderStatus, FoodOrderEvent> stateMachine) {
        log.debug("Pre-State Change");
        Optional.ofNullable(message)
                .flatMap(msg -> Optional.ofNullable((String) msg.getHeaders().getOrDefault(FoodOrderConstants.ORDER_ID_HEADER, " ")))
                .ifPresent(orderId -> {
                    // sleeping for 1 second just to mimic the time taken for each state transition
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    log.debug("Saving state for order id: " + orderId + " Status: " + state.getId());
                    FoodOrder foodOrder = foodOrderRepository.findById(orderId).get();
                    foodOrder.setOrderStatus(state.getId());
                    foodOrderRepository.save(foodOrder);
                });
    }
}
