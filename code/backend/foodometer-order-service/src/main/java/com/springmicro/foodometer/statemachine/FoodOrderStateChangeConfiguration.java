package com.springmicro.foodometer.statemachine;

import com.springmicro.foodometer.constants.FoodOrderEvent;
import com.springmicro.foodometer.constants.FoodOrderStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.action.Action;
import org.springframework.statemachine.config.EnableStateMachineFactory;
import org.springframework.statemachine.config.StateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;

import java.util.EnumSet;

@RequiredArgsConstructor
@Configuration
@EnableStateMachineFactory
public class FoodOrderStateChangeConfiguration extends StateMachineConfigurerAdapter<FoodOrderStatus, FoodOrderEvent> {

    private final Action<FoodOrderStatus, FoodOrderEvent> validateOrderAction;
    private final Action<FoodOrderStatus, FoodOrderEvent> validationFailureAction;

    @Override
    public void configure(StateMachineStateConfigurer<FoodOrderStatus, FoodOrderEvent> states) throws Exception {
        states.withStates()
                .initial(FoodOrderStatus.NEW)
                .states(EnumSet.allOf(FoodOrderStatus.class))
                .end(FoodOrderStatus.DELIVERED)
                .end(FoodOrderStatus.CANCELLED);
    }

    @Override
    public void configure(StateMachineTransitionConfigurer<FoodOrderStatus, FoodOrderEvent> transitions) throws Exception {
        transitions
                .withExternal()
                .source(FoodOrderStatus.NEW).target(FoodOrderStatus.PAYMENT_PROCESSING)
                .event(FoodOrderEvent.PAY)
                .action(validateOrderAction)

                .and().withExternal()
                .source(FoodOrderStatus.PAYMENT_PROCESSING).target(FoodOrderStatus.PLACED)
                .event(FoodOrderEvent.PAID)
                .action(validateOrderAction)

                .and().withExternal()
                .source(FoodOrderStatus.PAYMENT_PROCESSING).target(FoodOrderStatus.CANCELLED)
                .event(FoodOrderEvent.PAYMENT_FAILED)
                .action(validationFailureAction);
    }
}
