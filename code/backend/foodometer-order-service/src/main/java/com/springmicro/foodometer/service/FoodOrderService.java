package com.springmicro.foodometer.service;

import com.springmicro.foodometer.constants.FoodOrderStatus;
import com.springmicro.foodometer.document.FoodOrder;
import com.springmicro.foodometer.exception.OrderException;
import com.springmicro.foodometer.repository.FoodOrderRepository;
import com.springmicro.foodometer.web.dto.FoodOrderDto;
import com.springmicro.foodometer.web.mapper.FoodOrderMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class FoodOrderService {

    private final FoodOrderRepository foodOrderRepository;
    private final FoodOrderMapper foodOrderMapper;

    public List<FoodOrderDto> getAllOrdersByCustomerId(String customerId) {
        List<FoodOrder> foodOrders = foodOrderRepository.findAllByCustomerId(customerId);
        log.debug("Order list for customer id "+customerId+" : "+foodOrders);
        return foodOrders.stream().map(order -> foodOrderMapper.foodOrderToFoodOrderDto(order)).collect(Collectors.toList());
    }

    public FoodOrderDto getOrderByCustomerIdAndOrderId(String customerId, String orderId) {
        return foodOrderMapper.foodOrderToFoodOrderDto(foodOrderRepository.findOrderByCustomerIdAndOrderId(customerId, orderId));
    }

    public FoodOrderDto getOrderByOrderId(String orderId) {
        Optional<FoodOrder> optionalFoodOrder = foodOrderRepository.findById(orderId);
        if(optionalFoodOrder.isPresent()) {
            return foodOrderMapper.foodOrderToFoodOrderDto(optionalFoodOrder.get());
        } else {
            throw new OrderException("No Order details found for id : "+ orderId);
        }
    }

    @Transactional
    public FoodOrderDto saveOrder(FoodOrderDto foodOrderDto) {
        if(foodOrderDto != null) {
            if (foodOrderDto.getCustomerId() != null) {
                if(foodOrderDto.getCustomerAddressId() != null) {
                    foodOrderDto.setOrderStatus(FoodOrderStatus.NEW);
                    foodOrderDto.setOrderAmount(foodOrderDto.getOrderAmount() * (1 - applicableDiscountPercent(foodOrderDto)));
                    return foodOrderMapper.foodOrderToFoodOrderDto(foodOrderRepository.save(foodOrderMapper.foodOrderDtoToFoodOrder(foodOrderDto)));
                } else {
                    log.error("No customer address is associated with the order");
                    throw new OrderException("No customer address is associated with the order");
                }
            } else {
                log.error("No customer is associated with the order");
                throw new OrderException("No customer is associated with the order");
            }
        } else {
            log.error("Food Order object cannot be null");
            throw new OrderException("Food Order object cannot be null");
        }
    }

    private double applicableDiscountPercent(FoodOrderDto foodOrderDto) {
        LocalDateTime localDateTime = foodOrderDto.getOrderTimestamp();
        if(localDateTime.getMonthValue() % 3 == 0 && localDateTime.getDayOfWeek() == DayOfWeek.WEDNESDAY) {
            return 0.5;
        } else if(localDateTime.getMonthValue() % 2 == 0 && localDateTime.getDayOfMonth() == 29) {
            return 0.25;
        } else {
            return 0;
        }
    }

    @Transactional
    public void cancelOrder(String customerId, String orderId) {
        FoodOrderDto foodOrderDto = getOrderByCustomerIdAndOrderId(customerId, orderId);
        foodOrderDto.setOrderStatus(FoodOrderStatus.CANCELLED);
        foodOrderRepository.save(foodOrderMapper.foodOrderDtoToFoodOrder(foodOrderDto));
    }
}
