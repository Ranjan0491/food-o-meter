package com.springmicro.foodometer.service.listner;

import com.springmicro.foodometer.constants.FoodOrderConstants;
import com.springmicro.foodometer.constants.FoodOrderStatus;
import com.springmicro.foodometer.exception.OrderException;
import com.springmicro.foodometer.service.FoodOrderManager;
import com.springmicro.foodometer.service.FoodOrderService;
import com.springmicro.foodometer.web.dto.FoodOrderDto;
import com.springmicro.foodometer.web.dto.event.PrepareOrderRequest;
import com.springmicro.foodometer.web.mapper.FoodOrderMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class AllocateChefOrderJmsListener {
    private final FoodOrderMapper foodOrderMapper;
    private final FoodOrderManager foodOrderManager;
    private final FoodOrderService foodOrderService;

    @JmsListener(destination = FoodOrderConstants.ALLOCATE_CHEF_ORDER_QUEUE)
    public void listen(Message message){
        boolean valid = false;
        FoodOrderDto foodOrderDto = null;
        PrepareOrderRequest request = (PrepareOrderRequest) message.getPayload();
        log.info("Prepare Order request - "+request);

        if (request.getFoodOrderDto().getId() != null) {
            foodOrderDto = foodOrderService.getOrderByOrderId(request.getFoodOrderDto().getId());
            if(foodOrderDto.getOrderStatus() == FoodOrderStatus.PLACED) {
                foodOrderManager.allocateFoodOrder(foodOrderMapper.foodOrderDtoToFoodOrder(foodOrderDto));
                valid = true;
            }
        }

//        if(!valid) {
//            throw new OrderException("Order is invalid for prepartion. Details - "+foodOrderDto);
//        }
    }
}
