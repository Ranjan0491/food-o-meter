package com.springmicro.foodometer.statemachine;

import com.springmicro.foodometer.constants.FoodOrderConstants;
import com.springmicro.foodometer.constants.FoodOrderEvent;
import com.springmicro.foodometer.constants.FoodOrderStatus;
import com.springmicro.foodometer.document.FoodOrder;
import com.springmicro.foodometer.repository.FoodOrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.Message;
import org.springframework.statemachine.StateContext;
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
    @Override
    public void preStateChange(State<FoodOrderStatus, FoodOrderEvent> state,
                                Message<FoodOrderEvent> message, Transition<FoodOrderStatus,
                                FoodOrderEvent> transition,
                                StateMachine<FoodOrderStatus, FoodOrderEvent> stateMachine,
                                StateMachine<FoodOrderStatus, FoodOrderEvent> rootStateMachine) {
        Optional.ofNullable(message)
                .flatMap(msg -> Optional.ofNullable((String) msg.getHeaders().getOrDefault(FoodOrderConstants.ORDER_ID_HEADER, "")))
                .ifPresent(orderId -> {
                    // sleeping for 2 seconds just to mimic the time taken for each state transition
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    log.info("Saving state for order id: " + orderId + " Status: " + state.getId());
                    FoodOrder foodOrder = foodOrderRepository.findById(orderId).get();
                    foodOrder.setOrderStatus(state.getId());
                    foodOrderRepository.save(foodOrder);
                });
    }

    @Override
    public StateContext<FoodOrderStatus, FoodOrderEvent> preTransition(StateContext<FoodOrderStatus, FoodOrderEvent> stateContext) {
        log.info("In preTransition..");
        log.info(stateContext.getMessage().getHeaders().toString());
        log.info(stateContext.getMessage().getPayload().toString());
        return super.preTransition(stateContext);
    }

    @Override
    public StateContext<FoodOrderStatus, FoodOrderEvent> postTransition(StateContext<FoodOrderStatus, FoodOrderEvent> stateContext) {
        log.info("In postTransition..");
        log.info(stateContext.getMessage().getHeaders().toString());
        log.info(stateContext.getMessage().getPayload().toString());
        return super.postTransition(stateContext);
    }
}
