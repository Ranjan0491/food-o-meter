package com.springmicro.foodometer.service.job;

import com.springmicro.foodometer.constants.FoodOrderConstants;
import com.springmicro.foodometer.constants.FoodOrderStatus;
import com.springmicro.foodometer.repository.FoodOrderRepository;
import com.springmicro.foodometer.web.dto.event.PickUpOrderRequest;
import com.springmicro.foodometer.web.mapper.FoodOrderMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class AllocateDeliveryAgentForFoodOrderJob {

    private final FoodOrderRepository foodOrderRepository;
    private final FoodOrderMapper foodOrderMapper;
    private final JmsTemplate jmsTemplate;

    @Transactional
    @Scheduled(fixedRate = 1000)
    public void allocateOrders() {
        foodOrderRepository.findByOrderStatus(FoodOrderStatus.PREPARED).forEach(foodOrder -> {
            jmsTemplate.convertAndSend(FoodOrderConstants.ALLOCATE_DELIVERY_AGENT_ORDER_QUEUE, PickUpOrderRequest.builder()
                    .foodOrderDto(foodOrderMapper.foodOrderToFoodOrderDto(foodOrder))
                    .build());
            log.info("Sent Food Order allocation request to queue for order id " + foodOrder.getId());
        });
    }
}
