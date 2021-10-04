package com.springmicro.foodometer.service.job;

import com.springmicro.foodometer.constants.FoodOrderStatus;
import com.springmicro.foodometer.repository.FoodOrderRepository;
import com.springmicro.foodometer.service.FoodOrderManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class PrepareFoodOrderJob {
    private final FoodOrderManager foodOrderManager;
    private final FoodOrderRepository foodOrderRepository;

    @Transactional
    @Scheduled(fixedRate = 1000)
    public void checkForOrdersApplicableForPreparation() {
        foodOrderRepository.findByOrderStatus(FoodOrderStatus.PREPARING)
                .stream()
                .forEach(foodOrder -> foodOrderManager.foodOrderPreparationComplete(foodOrder));
    }

}
