package com.springmicro.foodometer.web.mapper;

import com.springmicro.foodometer.document.FoodItem;
import com.springmicro.foodometer.web.dto.FoodItemDto;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2021-09-27T09:40:39+0530",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 16.0.2 (Oracle Corporation)"
)
@Component
public class FoodItemMapperImpl implements FoodItemMapper {

    @Override
    public FoodItemDto foodItemToFoodItemDto(FoodItem foodItem) {
        if ( foodItem == null ) {
            return null;
        }

        FoodItemDto foodItemDto = new FoodItemDto();

        foodItemDto.setId( foodItem.getId() );
        foodItemDto.setCategory( foodItem.getCategory() );
        foodItemDto.setItemName( foodItem.getItemName() );
        foodItemDto.setItemPrice( foodItem.getItemPrice() );

        return foodItemDto;
    }

    @Override
    public FoodItem foodItemDtoToFoodItem(FoodItemDto foodItemDto) {
        if ( foodItemDto == null ) {
            return null;
        }

        FoodItem foodItem = new FoodItem();

        foodItem.setId( foodItemDto.getId() );
        foodItem.setCategory( foodItemDto.getCategory() );
        foodItem.setItemName( foodItemDto.getItemName() );
        foodItem.setItemPrice( foodItemDto.getItemPrice() );

        return foodItem;
    }
}
