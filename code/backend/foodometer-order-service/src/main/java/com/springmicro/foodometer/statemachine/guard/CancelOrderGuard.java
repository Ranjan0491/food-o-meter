package com.springmicro.foodometer.statemachine.guard;

import com.springmicro.foodometer.constants.FoodOrderConstants;
import com.springmicro.foodometer.constants.FoodOrderEvent;
import com.springmicro.foodometer.constants.FoodOrderStatus;
import com.springmicro.foodometer.exception.OrderException;
import com.springmicro.foodometer.repository.FoodOrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.guard.Guard;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class CancelOrderGuard implements Guard<FoodOrderStatus, FoodOrderEvent> {

    private final FoodOrderRepository foodOrderRepository;

    @Override
    public boolean evaluate(StateContext<FoodOrderStatus, FoodOrderEvent> stateContext) {
        String orderId = stateContext.getMessageHeader(FoodOrderConstants.ORDER_ID_HEADER).toString();
        if(foodOrderRepository.findById(orderId).filter(foodOrder -> foodOrder.getOrderStatus() == FoodOrderStatus.PLACED).isPresent()) {
            return true;
        } else {
            log.info("Cannot cancel order - "+orderId);
            throw new OrderException("Order cannot be cancelled now. ID - "+orderId);
        }
    }
}
