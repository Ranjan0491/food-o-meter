package com.springmicro.foodometer.statemachine.action;

import com.springmicro.foodometer.constants.FoodOrderConstants;
import com.springmicro.foodometer.constants.FoodOrderEvent;
import com.springmicro.foodometer.constants.FoodOrderStatus;
import com.springmicro.foodometer.document.FoodOrder;
import com.springmicro.foodometer.repository.FoodOrderRepository;
import com.springmicro.foodometer.web.dto.FoodOrderDto;
import com.springmicro.foodometer.web.dto.event.ValidateOrderRequest;

import com.springmicro.foodometer.web.mapper.FoodOrderMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.action.Action;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class ValidateOrderAction implements Action<FoodOrderStatus, FoodOrderEvent> {

    private final FoodOrderRepository foodOrderRepository;
    private final FoodOrderMapper foodOrderMapper;
    private final JmsTemplate jmsTemplate;

    @Override
    public void execute(StateContext<FoodOrderStatus, FoodOrderEvent> context) {
        String foodOrderId = (String) context.getMessage().getHeaders().get(FoodOrderConstants.ORDER_ID_HEADER);
        foodOrderRepository.findById(foodOrderId).ifPresentOrElse(foodOrder -> {
            jmsTemplate.convertAndSend(FoodOrderConstants.VALIDATE_ORDER_QUEUE, ValidateOrderRequest.builder()
                    .foodOrderDto(foodOrderMapper.foodOrderToFoodOrderDto(foodOrder))
                    .build());
            log.info("Sent Food Order Validation request to queue for order id " + foodOrderId);
        }, () -> log.error("Food order not found for id " + foodOrderId));
    }
}
