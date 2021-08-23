package com.springmicro.foodometer.web.mapper;

import com.springmicro.foodometer.document.FoodItem;
import com.springmicro.foodometer.document.FoodItem.FoodItemBuilder;
import com.springmicro.foodometer.web.dto.FoodItemDto;
import com.springmicro.foodometer.web.dto.FoodItemDto.FoodItemDtoBuilder;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2021-08-23T11:04:50+0530",
    comments = "version: 1.3.0.Final, compiler: javac, environment: Java 15.0.2 (Oracle Corporation)"
)
@Component
public class FoodItemMapperImpl implements FoodItemMapper {

    @Override
    public FoodItemDto foodItemToFoodItemDto(FoodItem foodItem) {
        if ( foodItem == null ) {
            return null;
        }

        FoodItemDtoBuilder foodItemDto = FoodItemDto.builder();

        foodItemDto.id( foodItem.getId() );
        foodItemDto.category( foodItem.getCategory() );
        foodItemDto.itemName( foodItem.getItemName() );
        foodItemDto.itemPrice( foodItem.getItemPrice() );

        return foodItemDto.build();
    }

    @Override
    public FoodItem foodItemDtoToFoodItem(FoodItemDto foodItemDto) {
        if ( foodItemDto == null ) {
            return null;
        }

        FoodItemBuilder foodItem = FoodItem.builder();

        foodItem.id( foodItemDto.getId() );
        foodItem.category( foodItemDto.getCategory() );
        foodItem.itemName( foodItemDto.getItemName() );
        foodItem.itemPrice( foodItemDto.getItemPrice() );

        return foodItem.build();
    }
}
