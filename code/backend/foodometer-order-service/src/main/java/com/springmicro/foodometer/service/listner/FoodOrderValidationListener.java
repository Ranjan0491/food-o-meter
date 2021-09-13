package com.springmicro.foodometer.service.listner;

import com.springmicro.foodometer.constants.FoodOrderConstants;
import com.springmicro.foodometer.constants.FoodOrderStatus;
import com.springmicro.foodometer.service.FoodOrderManager;
import com.springmicro.foodometer.web.dto.event.ValidateOrderRequest;
import com.springmicro.foodometer.web.dto.event.ValidateOrderResult;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class FoodOrderValidationListener {
    private final JmsTemplate jmsTemplate;
    private final FoodOrderManager foodOrderManager;

    @JmsListener(destination = FoodOrderConstants.VALIDATE_ORDER_QUEUE)
    public void listen(Message message){
        boolean isValid = true;

        ValidateOrderRequest request = (ValidateOrderRequest) message.getPayload();

        //condition to fail validation
        if (request.getFoodOrderDto().getId() != null) {
            if(request.getFoodOrderDto().getDiscountedAmount() == null && request.getFoodOrderDto().getDiscountedAmount() > 0) {
                isValid = false;
            }
        } else {
            isValid = false;
        }
        foodOrderManager.processValidationResult(request.getFoodOrderDto().getId(), isValid);
        jmsTemplate.convertAndSend(FoodOrderConstants.VALIDATE_ORDER_RESPONSE_QUEUE,
                    ValidateOrderResult.builder()
                            .isValidOrder(isValid)
                            .orderId(request.getFoodOrderDto().getId())
                            .build());
    }
}
