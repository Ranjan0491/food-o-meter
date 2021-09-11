package com.springmicro.foodometer.statemachine.action;

import com.springmicro.foodometer.constants.FoodOrderConstants;
import com.springmicro.foodometer.constants.FoodOrderEvent;
import com.springmicro.foodometer.document.FoodOrder;
import com.springmicro.foodometer.service.FoodOrderService;
import com.springmicro.foodometer.web.dto.FoodOrderDto;
import com.springmicro.foodometer.web.dto.event.ValidateOrderRequest;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.action.Action;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class ValidateOrderAction implements Action<FoodOrder, FoodOrderEvent> {

    private final FoodOrderService foodOrderService;
    private final JmsTemplate jmsTemplate;

    @Override
    public void execute(StateContext<FoodOrder, FoodOrderEvent> context) {
        String foodOrderId = (String) context.getMessage().getHeaders().get(FoodOrderConstants.ORDER_ID_HEADER);
        FoodOrderDto foodOrderDto = foodOrderService.getOrderByOrderId(foodOrderId);
        jmsTemplate.convertAndSend(FoodOrderConstants.VALIDATE_ORDER_QUEUE, ValidateOrderRequest.builder()
                    .foodOrderDto(foodOrderDto)
                    .build());
        log.debug("Sent Food Order Validation request to queue for order id " + foodOrderId);
    }
}
