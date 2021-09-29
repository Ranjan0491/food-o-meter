package com.springmicro.foodometer.statemachine;

import com.springmicro.foodometer.constants.FoodOrderEvent;
import com.springmicro.foodometer.constants.FoodOrderStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.action.Action;
import org.springframework.statemachine.config.EnableStateMachineFactory;
import org.springframework.statemachine.config.StateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineConfigurationConfigurer;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;

import java.util.EnumSet;

@RequiredArgsConstructor
@Configuration
@EnableStateMachineFactory
public class FoodOrderStateChangeConfiguration extends StateMachineConfigurerAdapter<FoodOrderStatus, FoodOrderEvent> {

    private final FoodOrderStateMachineListener foodOrderStateMachineListener;
    private final Action<FoodOrderStatus, FoodOrderEvent> allocateOrderAction;

    @Override
    public void configure(StateMachineConfigurationConfigurer<FoodOrderStatus, FoodOrderEvent> config) throws Exception {
        config.withConfiguration()
                .listener(foodOrderStateMachineListener);
    }

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
                .source(FoodOrderStatus.NEW).target(FoodOrderStatus.PLACED)
                .event(FoodOrderEvent.CONFIRM_ORDER)
                .action(allocateOrderAction)

                .and().withExternal()
                .source(FoodOrderStatus.PLACED).target(FoodOrderStatus.PREPARING)
                .event(FoodOrderEvent.PREPARE)

                .and().withExternal()
                .source(FoodOrderStatus.PLACED).target(FoodOrderStatus.CANCELLED)
                .event(FoodOrderEvent.CANCEL_ORDER)

                .and().withExternal()
                .source(FoodOrderStatus.PREPARING).target(FoodOrderStatus.PREPARED)
                .event(FoodOrderEvent.PREPARATION_COMPLETE)

                .and().withExternal()
                .source(FoodOrderStatus.PREPARED).target(FoodOrderStatus.PICKED_UP)
                .event(FoodOrderEvent.READY_FOR_PICKUP)

                .and().withExternal()
                .source(FoodOrderStatus.PICKED_UP).target(FoodOrderStatus.ON_THE_WAY)
                .event(FoodOrderEvent.ORDER_PICKED_UP)

                .and().withExternal()
                .source(FoodOrderStatus.ON_THE_WAY).target(FoodOrderStatus.DELIVERED)
                .event(FoodOrderEvent.CONFIRM_DELIVERY);
    }
}
