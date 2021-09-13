package com.springmicro.foodometer.web.mapper;

import com.springmicro.foodometer.document.FoodItemQuantity;
import com.springmicro.foodometer.document.FoodOrder;
import com.springmicro.foodometer.web.dto.FoodItemQuantityDto;
import com.springmicro.foodometer.web.dto.FoodOrderDto;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2021-09-13T10:43:19+0530",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 16.0.2 (Oracle Corporation)"
)
@Component
public class FoodOrderMapperImpl implements FoodOrderMapper {

    @Override
    public FoodOrderDto foodOrderToFoodOrderDto(FoodOrder foodOrder) {
        if ( foodOrder == null ) {
            return null;
        }

        FoodOrderDto foodOrderDto = new FoodOrderDto();

        foodOrderDto.setId( foodOrder.getId() );
        foodOrderDto.setFoodItems( foodItemQuantityListToFoodItemQuantityDtoList( foodOrder.getFoodItems() ) );
        foodOrderDto.setCustomerId( foodOrder.getCustomerId() );
        foodOrderDto.setCustomerAddressId( foodOrder.getCustomerAddressId() );
        foodOrderDto.setOrderTimestamp( foodOrder.getOrderTimestamp() );
        foodOrderDto.setOrderAmount( foodOrder.getOrderAmount() );
        foodOrderDto.setOrderStatus( foodOrder.getOrderStatus() );
        foodOrderDto.setDiscount( foodOrder.getDiscount() );
        foodOrderDto.setDiscountedAmount( foodOrder.getDiscountedAmount() );

        return foodOrderDto;
    }

    @Override
    public FoodOrder foodOrderDtoToFoodOrder(FoodOrderDto foodOrderDto) {
        if ( foodOrderDto == null ) {
            return null;
        }

        FoodOrder foodOrder = new FoodOrder();

        foodOrder.setId( foodOrderDto.getId() );
        foodOrder.setFoodItems( foodItemQuantityDtoListToFoodItemQuantityList( foodOrderDto.getFoodItems() ) );
        foodOrder.setCustomerId( foodOrderDto.getCustomerId() );
        foodOrder.setCustomerAddressId( foodOrderDto.getCustomerAddressId() );
        foodOrder.setOrderTimestamp( foodOrderDto.getOrderTimestamp() );
        foodOrder.setOrderAmount( foodOrderDto.getOrderAmount() );
        foodOrder.setOrderStatus( foodOrderDto.getOrderStatus() );
        foodOrder.setDiscount( foodOrderDto.getDiscount() );
        foodOrder.setDiscountedAmount( foodOrderDto.getDiscountedAmount() );

        return foodOrder;
    }

    protected FoodItemQuantityDto foodItemQuantityToFoodItemQuantityDto(FoodItemQuantity foodItemQuantity) {
        if ( foodItemQuantity == null ) {
            return null;
        }

        FoodItemQuantityDto foodItemQuantityDto = new FoodItemQuantityDto();

        foodItemQuantityDto.setFoodItemId( foodItemQuantity.getFoodItemId() );
        foodItemQuantityDto.setQuantity( foodItemQuantity.getQuantity() );

        return foodItemQuantityDto;
    }

    protected List<FoodItemQuantityDto> foodItemQuantityListToFoodItemQuantityDtoList(List<FoodItemQuantity> list) {
        if ( list == null ) {
            return null;
        }

        List<FoodItemQuantityDto> list1 = new ArrayList<FoodItemQuantityDto>( list.size() );
        for ( FoodItemQuantity foodItemQuantity : list ) {
            list1.add( foodItemQuantityToFoodItemQuantityDto( foodItemQuantity ) );
        }

        return list1;
    }

    protected FoodItemQuantity foodItemQuantityDtoToFoodItemQuantity(FoodItemQuantityDto foodItemQuantityDto) {
        if ( foodItemQuantityDto == null ) {
            return null;
        }

        FoodItemQuantity foodItemQuantity = new FoodItemQuantity();

        foodItemQuantity.setFoodItemId( foodItemQuantityDto.getFoodItemId() );
        foodItemQuantity.setQuantity( foodItemQuantityDto.getQuantity() );

        return foodItemQuantity;
    }

    protected List<FoodItemQuantity> foodItemQuantityDtoListToFoodItemQuantityList(List<FoodItemQuantityDto> list) {
        if ( list == null ) {
            return null;
        }

        List<FoodItemQuantity> list1 = new ArrayList<FoodItemQuantity>( list.size() );
        for ( FoodItemQuantityDto foodItemQuantityDto : list ) {
            list1.add( foodItemQuantityDtoToFoodItemQuantity( foodItemQuantityDto ) );
        }

        return list1;
    }
}
