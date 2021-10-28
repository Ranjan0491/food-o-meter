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
public class FoodOrderDto {
    private String id;
    private List<FoodItemQuantityDto> foodItems;
    private String customerId;
    private String customerAddressId;
    @JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss", shape=JsonFormat.Shape.STRING)
    private LocalDateTime orderTimestamp;
    private Double orderAmount;
    private FoodOrderStatus orderStatus;
    private Double discount;
    private Double payableAmount;
}
