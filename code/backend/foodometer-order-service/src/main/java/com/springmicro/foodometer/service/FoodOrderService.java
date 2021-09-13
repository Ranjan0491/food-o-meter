package com.springmicro.foodometer.service;

import com.springmicro.foodometer.constants.FoodOrderConstants;
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
import org.springframework.web.client.RestTemplate;

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
    private final RestTemplate restTemplate;
    private final FoodOrderManager foodOrderManager;

    public List<FoodOrderDto> getAllOrdersByCustomerId(String customerId) {
        List<FoodOrder> foodOrders = foodOrderRepository.findAllByCustomerId(customerId);
        log.info("Order list for customer id "+customerId+" : "+foodOrders);
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
                UserDto customer = fetchUserDto(foodOrderDto.getCustomerId());
                if(customer != null) {
                    if (foodOrderDto.getCustomerAddressId() != null) {
                        if(customer.getAddresses().stream().anyMatch(address -> address.getId().equals(foodOrderDto.getCustomerAddressId()))) {
                            foodOrderDto.setOrderTimestamp(LocalDateTime.now());
                            if (validateFoodItemsInOrder(foodOrderDto)) {
                                Double orderAmount = calculateTotalAmount(foodOrderDto);
                                Double discount = applicableDiscountPercent(foodOrderDto);
                                foodOrderDto.setOrderAmount(orderAmount);
                                foodOrderDto.setDiscount(discount * 100);
                                foodOrderDto.setDiscountedAmount(orderAmount * (1 - discount));
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
    public void cancelOrder(String customerId, String orderId) {
        FoodOrderDto foodOrderDto = getOrderByCustomerIdAndOrderId(customerId, orderId);
        foodOrderDto.setOrderStatus(FoodOrderStatus.CANCELLED);
        foodOrderRepository.save(foodOrderMapper.foodOrderDtoToFoodOrder(foodOrderDto));
    }

    private FoodItemDto fetchFoodItemDto(String id) {
        return restTemplate.getForObject("http://" + FoodOrderConstants.FOOD_ITEM_SERVICE_NAME + "/food-o-meter-item-service/v1/food-items/" + id, FoodItemDto.class);
    }

    private boolean validateFoodItemsInOrder(FoodOrderDto foodOrderDto) {
        return foodOrderDto.getFoodItems().stream().allMatch(itemQuantity -> {
            FoodItemDto food = fetchFoodItemDto(itemQuantity.getFoodItemId());
            return food != null;
        });
    }

    private Double calculateTotalAmount(FoodOrderDto foodOrderDto) {
        Double totalAmount = 0d;
        for(FoodItemQuantityDto foodItemQuantityDto: foodOrderDto.getFoodItems()) {
            FoodItemDto foodItemDto = fetchFoodItemDto(foodItemQuantityDto.getFoodItemId());
            totalAmount += foodItemDto.getItemPrice() * foodItemQuantityDto.getQuantity();
        }
        return totalAmount;
    }

    private UserDto fetchUserDto(String id) {
        return restTemplate.getForObject("http://" + FoodOrderConstants.FOOD_USER_SERVICE_NAME + "/food-o-meter-user-service/v1/users/" + id, UserDto.class);
    }
}
