package com.springmicro.foodometer.service.job;

import com.springmicro.foodometer.constants.FoodOrderEvent;
import com.springmicro.foodometer.constants.FoodOrderStatus;
import com.springmicro.foodometer.repository.FoodOrderRepository;
import com.springmicro.foodometer.service.FoodOrderManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;

@Service
@RequiredArgsConstructor
@Slf4j
public class DeliverFoodOrderJob {
    private final FoodOrderManager foodOrderManager;
    private final FoodOrderRepository foodOrderRepository;

    @Transactional
    @Scheduled(fixedRate = 1000)
    public void checkForOrdersApplicableForDelivery() {
        foodOrderRepository.findByOrderStatusIn(Arrays.asList(new FoodOrderStatus[] {FoodOrderStatus.PICKED_UP, FoodOrderStatus.ON_THE_WAY}))
                .parallelStream()
                .forEach(foodOrder -> {
                    if(FoodOrderStatus.PICKED_UP == foodOrder.getOrderStatus()) {
                        foodOrderManager.foodOrderProcessDelivery(foodOrder, FoodOrderStatus.ON_THE_WAY, FoodOrderEvent.OUT_FOR_DELIVERY, 3);
                    } else {
                        foodOrderManager.foodOrderProcessDelivery(foodOrder, FoodOrderStatus.DELIVERED, FoodOrderEvent.CONFIRM_DELIVERY, 6);
                    }
                });
    }

}
