package com.springmicro.foodometer.web.mapper;

import com.springmicro.foodometer.document.FoodOrder;
import com.springmicro.foodometer.web.dto.FoodOrderDto;
import org.mapstruct.Mapper;

@Mapper
public interface FoodOrderMapper {
    FoodOrderDto foodOrderToFoodOrderDto(FoodOrder foodOrder);
    FoodOrder foodOrderDtoToFoodOrder(FoodOrderDto foodOrderDto);
}
