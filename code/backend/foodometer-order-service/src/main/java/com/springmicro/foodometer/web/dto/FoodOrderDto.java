package com.springmicro.foodometer.web.dto;

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
public class FoodOrderDto {
    private String id;
    private List<String> foodItemIds;
    private String customerId;
    private String customerAddressId;
    private LocalDateTime orderTimestamp;
    private Double orderAmount;
    private FoodOrderStatus orderStatus;
}
