package com.springmicro.foodometer.web.mapper;

import com.springmicro.foodometer.document.FoodOrder;
import com.springmicro.foodometer.web.dto.DetailedFoodOrderDto;
import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;

//@Mapper
//@DecoratedWith(DetailedFoodOrderMapperDecorator.class)
public interface DetailedFoodOrderMapper {
    DetailedFoodOrderDto foodOrderToDetailedFoodOrderDto(FoodOrder foodOrder);
}
