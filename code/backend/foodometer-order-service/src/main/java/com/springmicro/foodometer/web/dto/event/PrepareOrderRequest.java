package com.springmicro.foodometer.web.dto.event;

import com.springmicro.foodometer.web.dto.FoodOrderDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PrepareOrderRequest {
    private FoodOrderDto foodOrderDto;
}
