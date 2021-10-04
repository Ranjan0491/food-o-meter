package com.springmicro.foodometer.service.listner;

import com.springmicro.foodometer.constants.FoodOrderConstants;
import com.springmicro.foodometer.constants.FoodOrderStatus;
import com.springmicro.foodometer.service.FoodOrderManager;
import com.springmicro.foodometer.service.FoodOrderService;
import com.springmicro.foodometer.web.dto.FoodOrderDto;
import com.springmicro.foodometer.web.dto.event.PickUpOrderRequest;
import com.springmicro.foodometer.web.mapper.FoodOrderMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class AllocateDeliveryAgentOrderJmsListener {
    private final FoodOrderMapper foodOrderMapper;
    private final FoodOrderManager foodOrderManager;
    private final FoodOrderService foodOrderService;

    @JmsListener(destination = FoodOrderConstants.ALLOCATE_DELIVERY_AGENT_ORDER_QUEUE)
    public void listen(Message message){
        FoodOrderDto foodOrderDto = null;
        PickUpOrderRequest request = (PickUpOrderRequest) message.getPayload();
        log.info("Pick up Order request - "+request);

        if (request.getFoodOrderDto().getId() != null) {
            foodOrderDto = foodOrderService.getOrderByOrderId(request.getFoodOrderDto().getId());
            if(foodOrderDto.getOrderStatus() == FoodOrderStatus.PREPARED) {
                foodOrderManager.assignDeliveryAgentForFoodOrder(foodOrderMapper.foodOrderDtoToFoodOrder(foodOrderDto));
            }
        }
    }
}
