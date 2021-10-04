package com.springmicro.foodometer.web.mapper;

import com.springmicro.foodometer.document.FoodOrder;
import com.springmicro.foodometer.web.dto.DetailedFoodOrderDto;
import com.springmicro.foodometer.web.dto.FoodOrderDto;
import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;

@Mapper
@DecoratedWith(DetailedFoodOrderMapperDecorator.class)
public interface FoodOrderMapper {
    FoodOrderDto foodOrderToFoodOrderDto(FoodOrder foodOrder);
    FoodOrder foodOrderDtoToFoodOrder(FoodOrderDto foodOrderDto);
    DetailedFoodOrderDto foodOrderToDetailedFoodOrderDto(FoodOrder foodOrder);
}
