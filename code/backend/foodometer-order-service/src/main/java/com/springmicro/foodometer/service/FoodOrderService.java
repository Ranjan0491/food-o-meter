package com.springmicro.foodometer.service;

import com.google.common.base.Enums;
import com.springmicro.foodometer.constants.FoodOrderStatus;
import com.springmicro.foodometer.document.FoodOrder;
import com.springmicro.foodometer.exception.OrderException;
import com.springmicro.foodometer.repository.FoodOrderRepository;
import com.springmicro.foodometer.web.dto.*;
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
    private final ItemLookupService itemLookupService;
    private final FoodOrderManager foodOrderManager;
    private final UserLookUpService userLookUpService;

    public List<FoodOrderDto> getAllOrdersByCustomerId(String customerId) {
        List<FoodOrder> foodOrders = foodOrderRepository.findAllByCustomerId(customerId);
        log.info("Order list for customer id "+customerId+" : "+foodOrders);
        return foodOrders.stream().map(order -> foodOrderMapper.foodOrderToFoodOrderDto(order)).collect(Collectors.toList());
    }

    public DetailedFoodOrderDto getDetailedOrderByCustomerIdAndOrderId(String customerId, String orderId) {
        Optional<FoodOrder> optionalFoodOrder = foodOrderRepository.findById(orderId);
        if(optionalFoodOrder.isPresent()) {
            FoodOrder foodOrder = optionalFoodOrder.get();
            if(foodOrder.getCustomerId().equals(customerId)) {
                return foodOrderMapper.foodOrderToDetailedFoodOrderDto(foodOrder);
            } else {
                throw new OrderException("Food order does not belong to specified customer.");
            }
        } else {
            throw new OrderException("Food order does not exist.");
        }
    }

    public FoodOrderDto getOrderByCustomerIdAndOrderId(String customerId, String orderId) {
        Optional<FoodOrder> optionalFoodOrder = foodOrderRepository.findById(orderId);
        if(optionalFoodOrder.isPresent()) {
            FoodOrder foodOrder = optionalFoodOrder.get();
            if(foodOrder.getCustomerId().equals(customerId)) {
                return foodOrderMapper.foodOrderToFoodOrderDto(foodOrder);
            } else {
                throw new OrderException("Food order does not belong to specified customer.");
            }
        } else {
            throw new OrderException("Food order does not exist.");
        }
    }

    public FoodOrderDto getOrderByOrderId(String orderId) {
        Optional<FoodOrder> optionalFoodOrder = foodOrderRepository.findById(orderId);
        if(optionalFoodOrder.isPresent()) {
            return foodOrderMapper.foodOrderToFoodOrderDto(optionalFoodOrder.get());
        } else {
            throw new OrderException("No Order details found for id : "+ orderId);
        }
    }

    public DetailedFoodOrderDto getDetailedOrderByOrderId(String orderId) {
        Optional<FoodOrder> optionalFoodOrder = foodOrderRepository.findById(orderId);
        if(optionalFoodOrder.isPresent()) {
            return foodOrderMapper.foodOrderToDetailedFoodOrderDto(optionalFoodOrder.get());
        } else {
            throw new OrderException("No Order details found for id : "+ orderId);
        }
    }

    @Transactional
    public FoodOrderDto saveOrder(FoodOrderDto foodOrderDto) {
        if(foodOrderDto != null) {
            if (foodOrderDto.getCustomerId() != null) {
                UserDto customer = userLookUpService.fetchUserDto(foodOrderDto.getCustomerId());
                if(customer != null) {
                    if (foodOrderDto.getCustomerAddressId() != null) {
                        if(customer.getAddresses().stream().anyMatch(address -> address.getId().equals(foodOrderDto.getCustomerAddressId()))) {
                            foodOrderDto.setOrderTimestamp(LocalDateTime.now());
                            if (validateFoodItemsInOrder(foodOrderDto)) {
                                Double orderAmount = calculateTotalAmount(foodOrderDto);
                                Double discount = applicableDiscountPercent(foodOrderDto);
                                foodOrderDto.setOrderAmount(orderAmount);
                                foodOrderDto.setDiscount(discount * 100);
                                foodOrderDto.setPayableAmount(orderAmount * (1 - discount));
                                FoodOrder foodOrder = foodOrderRepository.save(foodOrderMapper.foodOrderDtoToFoodOrder(foodOrderDto));
                                foodOrderManager.newFoodOrder(foodOrder);
                                return foodOrderMapper.foodOrderToFoodOrderDto(foodOrder);
                            } else {
                                log.error("All Food Items does not match the repository");
                                throw new OrderException("All Food Items does not match the repository");
                            }
                        } else {
                            log.error("Not a valid customer address provided for the order");
                            throw new OrderException("Not a valid customer address provided for the order");
                        }
                    } else {
                        log.error("No customer address is associated with the order");
                        throw new OrderException("No customer address is associated with the order");
                    }
                } else {
                    log.error("Not a valid customer is placing the order");
                    throw new OrderException("Not a valid customer is placing the order");
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

    /**
     * Random discount on orders
     * @param foodOrderDto
     * @return
     */
    private double applicableDiscountPercent(FoodOrderDto foodOrderDto) {
        LocalDateTime localDateTime = foodOrderDto.getOrderTimestamp();
        if(localDateTime.getMonthValue() % 3 == 0 && localDateTime.getDayOfWeek() == DayOfWeek.WEDNESDAY) {
            return 0.5;
        } else if(localDateTime.getMonthValue() % 2 == 0 && localDateTime.getDayOfMonth() == 29) {
            return 0.25;
        } else if(localDateTime.getDayOfMonth() == 1) {
            return 0.1;
        } else {
            return 0;
        }
    }

    @Transactional
    public FoodOrderDto cancelOrder(String customerId, String orderId) {
        FoodOrderDto foodOrderDto = getOrderByCustomerIdAndOrderId(customerId, orderId);
        FoodOrder cancelledFoodOrder = foodOrderManager.cancelFoodOrder(foodOrderMapper.foodOrderDtoToFoodOrder(foodOrderDto));
        return foodOrderMapper.foodOrderToFoodOrderDto(cancelledFoodOrder);
    }

    public List<FoodOrderDto> getAllFoodOrdersByStatus(FoodOrderStatus foodOrderStatus) {
        return foodOrderRepository.findByOrderStatus(foodOrderStatus)
                .stream()
                .map(foodOrder -> foodOrderMapper.foodOrderToFoodOrderDto(foodOrder))
                .collect(Collectors.toList());
    }

    public List<FoodOrderDto> getAllFoodOrdersByStatus(String status) {
        return getAllFoodOrdersByStatus(Enums.getIfPresent(FoodOrderStatus.class, status).get());
    }

    private boolean validateFoodItemsInOrder(FoodOrderDto foodOrderDto) {
        return foodOrderDto.getFoodItems().stream().allMatch(itemQuantity -> {
            FoodItemDto food = itemLookupService.fetchFoodItemDto(itemQuantity.getFoodItemId());
            return food != null;
        });
    }

    private Double calculateTotalAmount(FoodOrderDto foodOrderDto) {
        Double totalAmount = 0d;
        for(FoodItemQuantityDto foodItemQuantityDto: foodOrderDto.getFoodItems()) {
            FoodItemDto foodItemDto = itemLookupService.fetchFoodItemDto(foodItemQuantityDto.getFoodItemId());
            totalAmount += foodItemDto.getItemPrice() * foodItemQuantityDto.getQuantity();
        }
        return totalAmount;
    }


}
