package com.springmicro.foodometer.web.dto;

import com.springmicro.foodometer.constants.FoodItemCategoryEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FoodItemDto {

    private String id;
    private FoodItemCategoryEnum category;
    private String itemName;
    private Double itemPrice;
}
