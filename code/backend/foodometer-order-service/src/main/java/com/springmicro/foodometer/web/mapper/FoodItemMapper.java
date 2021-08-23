package com.springmicro.foodometer.web.mapper;

import com.springmicro.foodometer.document.FoodItem;
import com.springmicro.foodometer.web.dto.FoodItemDto;
import org.mapstruct.Mapper;

@Mapper
public interface FoodItemMapper {

    FoodItemDto foodItemToFoodItemDto(FoodItem foodItem);

    FoodItem foodItemDtoToFoodItem(FoodItemDto foodItemDto);
}
