package com.springmicro.foodometer.statemachine.action;

import com.springmicro.foodometer.constants.FoodOrderConstants;
import com.springmicro.foodometer.constants.FoodOrderEvent;
import com.springmicro.foodometer.constants.FoodOrderStatus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.action.Action;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ValidationFailureAction implements Action<FoodOrderStatus, FoodOrderEvent> {

    @Override
    public void execute(StateContext<FoodOrderStatus, FoodOrderEvent> context) {
        String foodOrderId = (String) context.getMessage().getHeaders().get(FoodOrderConstants.ORDER_ID_HEADER);
        log.error("Compensating Transaction.... Validation Failed: " + foodOrderId);
    }
}
