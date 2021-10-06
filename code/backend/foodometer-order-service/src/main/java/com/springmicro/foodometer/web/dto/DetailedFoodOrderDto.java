package com.springmicro.foodometer.web.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.springmicro.foodometer.constants.FoodOrderStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DetailedFoodOrderDto {

    private String id;
    private List<DetailedFoodItemQuantityDto> foodItems;
    @JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss", shape=JsonFormat.Shape.STRING)
    private LocalDateTime orderTimestamp;
    private Double orderAmount;
    private FoodOrderStatus orderStatus;
    private Double discount;
    private Double payableAmount;
    private UserDto customer;
    private AddressDto orderAddress;
    private StaffDto chef;
    private StaffDto deliveryAgent;
}
