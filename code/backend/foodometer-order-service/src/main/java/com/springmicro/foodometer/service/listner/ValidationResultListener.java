package com.springmicro.foodometer.service.listner;

import com.springmicro.foodometer.constants.FoodOrderConstants;
import com.springmicro.foodometer.service.FoodOrderManager;
import com.springmicro.foodometer.web.dto.event.ValidateOrderResult;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class ValidationResultListener {

    private final FoodOrderManager foodOrderManager;

    @JmsListener(destination = FoodOrderConstants.VALIDATE_ORDER_RESPONSE_QUEUE)
    public void listen(ValidateOrderResult result){
        final String foodOrderId = result.getOrderId();
        log.debug("Validation Result for Order Id: " + foodOrderId);
//        foodOrderManager.processValidationResult(foodOrderId, result.getIsValidOrder());
    }
}
