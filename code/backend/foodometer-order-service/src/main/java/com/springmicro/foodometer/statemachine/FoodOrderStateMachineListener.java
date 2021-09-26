package com.springmicro.foodometer.statemachine;

import com.springmicro.foodometer.constants.FoodOrderEvent;
import com.springmicro.foodometer.constants.FoodOrderStatus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.listener.StateMachineListenerAdapter;
import org.springframework.statemachine.state.State;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class FoodOrderStateMachineListener extends StateMachineListenerAdapter<FoodOrderStatus, FoodOrderEvent> {

    @Override
    public void stateChanged(State<FoodOrderStatus, FoodOrderEvent> from, State<FoodOrderStatus, FoodOrderEvent> to) {
        log.info("SM Listener stateChanged: " + (from == null ? null : from.getId()) + " --> " + (to == null ? null : to.getId()));
        super.stateChanged(from, to);
    }

    @Override
    public void stateExited(State<FoodOrderStatus, FoodOrderEvent> state) {
        log.info("SM Listener stateExited: " + state.getId());
        super.stateExited(state);
    }

    @Override
    public void stateEntered(State<FoodOrderStatus, FoodOrderEvent> state) {
        log.info("SM Listener stateEntered: " + state.getId());
        super.stateEntered(state);
    }

    @Override
    public void stateMachineStarted(StateMachine<FoodOrderStatus, FoodOrderEvent> stateMachine) {
        log.info("SM Listener stateMachineStarted: " + stateMachine.getId() + "  has error: " + stateMachine.hasStateMachineError());
        super.stateMachineStarted(stateMachine);
    }

    @Override
    public void stateMachineError(StateMachine<FoodOrderStatus, FoodOrderEvent> stateMachine, Exception exception) {
        log.info("SM Listener stateMachineError: " + stateMachine.getId() + "  has error: " + stateMachine.hasStateMachineError());
        super.stateMachineError(stateMachine, exception);
    }

    @Override
    public void stateMachineStopped(StateMachine<FoodOrderStatus, FoodOrderEvent> stateMachine) {
        log.info("SM Listener stateMachineStopped: " + stateMachine.getId() + "  has error: " + stateMachine.hasStateMachineError());
        super.stateMachineStopped(stateMachine);
    }
}
