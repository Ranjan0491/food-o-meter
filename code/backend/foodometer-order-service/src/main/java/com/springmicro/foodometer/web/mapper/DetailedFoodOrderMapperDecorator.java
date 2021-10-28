package com.springmicro.foodometer.web.mapper;

import com.springmicro.foodometer.document.FoodOrder;
import com.springmicro.foodometer.document.FoodOrderDelivery;
import com.springmicro.foodometer.document.FoodOrderPreparation;
import com.springmicro.foodometer.repository.FoodOrderDeliveryRepository;
import com.springmicro.foodometer.repository.FoodOrderPreparationRepository;
import com.springmicro.foodometer.service.ItemLookupService;
import com.springmicro.foodometer.service.UserLookUpService;
import com.springmicro.foodometer.web.dto.DetailedFoodItemQuantityDto;
import com.springmicro.foodometer.web.dto.DetailedFoodOrderDto;
import com.springmicro.foodometer.web.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.stream.Collectors;

public abstract class DetailedFoodOrderMapperDecorator implements FoodOrderMapper{

    @Autowired
    @Qualifier("delegate")
    private FoodOrderMapper foodOrderMapper;

    @Autowired
    private FoodOrderDeliveryRepository foodOrderDeliveryRepository;

    @Autowired
    private FoodOrderPreparationRepository foodOrderPreparationRepository;

    @Autowired
    private UserLookUpService userLookUpService;

    @Autowired
    private ItemLookupService itemLookupService;

    @Override
    public DetailedFoodOrderDto foodOrderToDetailedFoodOrderDto(FoodOrder foodOrder) {
        if(foodOrder != null) {
            DetailedFoodOrderDto detailedFoodOrderDto = DetailedFoodOrderDto.builder()
                    .id(foodOrder.getId())
                    .discount(foodOrder.getDiscount())
                    .payableAmount(foodOrder.getPayableAmount())
                    .orderAmount(foodOrder.getOrderAmount())
                    .orderTimestamp(foodOrder.getOrderTimestamp())
                    .orderStatus(foodOrder.getOrderStatus())
                    .foodItems(foodOrder.getFoodItems()
                            .stream()
                            .map(foodItemQuantity -> DetailedFoodItemQuantityDto.builder()
                                    .quantity(foodItemQuantity.getQuantity())
                                    .foodItemDto(itemLookupService.fetchFoodItemDto(foodItemQuantity.getFoodItemId()))
                                    .build())
                            .collect(Collectors.toList()))
                    .build();
            UserDto customerDetails = userLookUpService.fetchUserDto(foodOrder.getCustomerId());
            detailedFoodOrderDto.setCustomer(customerDetails);
            detailedFoodOrderDto.setOrderAddress(customerDetails.getAddresses()
                    .stream()
                    .filter(addressDto -> addressDto.getId().equals(foodOrder.getCustomerAddressId()))
                    .findFirst()
                    .orElse(null));
            FoodOrderPreparation foodOrderPreparation = foodOrderPreparationRepository.findByFoodOrderId(foodOrder.getId());
            FoodOrderDelivery foodOrderDelivery = foodOrderDeliveryRepository.findByFoodOrderId(foodOrder.getId());
            if(foodOrderPreparation != null) {
                detailedFoodOrderDto.setChef(userLookUpService.fetchStaffById(foodOrderPreparation.getStaffId()));
            }
            if(foodOrderDelivery != null) {
                detailedFoodOrderDto.setDeliveryAgent(userLookUpService.fetchStaffById(foodOrderDelivery.getStaffId()));
            }

            return detailedFoodOrderDto;
        } else {
            return null;
        }
    }
}
