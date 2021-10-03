package com.springmicro.foodometer.web.mapper;

import com.springmicro.foodometer.document.FoodItemQuantity;
import com.springmicro.foodometer.document.FoodItemQuantity.FoodItemQuantityBuilder;
import com.springmicro.foodometer.document.FoodOrder;
import com.springmicro.foodometer.document.FoodOrder.FoodOrderBuilder;
import com.springmicro.foodometer.web.dto.DetailedFoodOrderDto;
import com.springmicro.foodometer.web.dto.DetailedFoodOrderDto.DetailedFoodOrderDtoBuilder;
import com.springmicro.foodometer.web.dto.FoodItemQuantityDto;
import com.springmicro.foodometer.web.dto.FoodItemQuantityDto.FoodItemQuantityDtoBuilder;
import com.springmicro.foodometer.web.dto.FoodOrderDto;
import com.springmicro.foodometer.web.dto.FoodOrderDto.FoodOrderDtoBuilder;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2021-10-03T19:38:52+0530",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 16.0.2 (Oracle Corporation)"
)
@Component
@Qualifier("delegate")
public class FoodOrderMapperImpl_ implements FoodOrderMapper {

    @Override
    public FoodOrderDto foodOrderToFoodOrderDto(FoodOrder foodOrder) {
        if ( foodOrder == null ) {
            return null;
        }

        FoodOrderDtoBuilder foodOrderDto = FoodOrderDto.builder();

        foodOrderDto.id( foodOrder.getId() );
        foodOrderDto.foodItems( foodItemQuantityListToFoodItemQuantityDtoList( foodOrder.getFoodItems() ) );
        foodOrderDto.customerId( foodOrder.getCustomerId() );
        foodOrderDto.customerAddressId( foodOrder.getCustomerAddressId() );
        foodOrderDto.orderTimestamp( foodOrder.getOrderTimestamp() );
        foodOrderDto.orderAmount( foodOrder.getOrderAmount() );
        foodOrderDto.orderStatus( foodOrder.getOrderStatus() );
        foodOrderDto.discount( foodOrder.getDiscount() );
        foodOrderDto.discountedAmount( foodOrder.getDiscountedAmount() );

        return foodOrderDto.build();
    }

    @Override
    public FoodOrder foodOrderDtoToFoodOrder(FoodOrderDto foodOrderDto) {
        if ( foodOrderDto == null ) {
            return null;
        }

        FoodOrderBuilder foodOrder = FoodOrder.builder();

        foodOrder.id( foodOrderDto.getId() );
        foodOrder.foodItems( foodItemQuantityDtoListToFoodItemQuantityList( foodOrderDto.getFoodItems() ) );
        foodOrder.customerId( foodOrderDto.getCustomerId() );
        foodOrder.customerAddressId( foodOrderDto.getCustomerAddressId() );
        foodOrder.orderTimestamp( foodOrderDto.getOrderTimestamp() );
        foodOrder.orderAmount( foodOrderDto.getOrderAmount() );
        foodOrder.orderStatus( foodOrderDto.getOrderStatus() );
        foodOrder.discount( foodOrderDto.getDiscount() );
        foodOrder.discountedAmount( foodOrderDto.getDiscountedAmount() );

        return foodOrder.build();
    }

    @Override
    public DetailedFoodOrderDto foodOrderToDetailedFoodOrderDto(FoodOrder foodOrder) {
        if ( foodOrder == null ) {
            return null;
        }

        DetailedFoodOrderDtoBuilder detailedFoodOrderDto = DetailedFoodOrderDto.builder();

        detailedFoodOrderDto.id( foodOrder.getId() );
        detailedFoodOrderDto.foodItems( foodItemQuantityListToFoodItemQuantityDtoList( foodOrder.getFoodItems() ) );
        detailedFoodOrderDto.orderTimestamp( foodOrder.getOrderTimestamp() );
        detailedFoodOrderDto.orderAmount( foodOrder.getOrderAmount() );
        detailedFoodOrderDto.orderStatus( foodOrder.getOrderStatus() );
        detailedFoodOrderDto.discount( foodOrder.getDiscount() );
        detailedFoodOrderDto.discountedAmount( foodOrder.getDiscountedAmount() );

        return detailedFoodOrderDto.build();
    }

    protected FoodItemQuantityDto foodItemQuantityToFoodItemQuantityDto(FoodItemQuantity foodItemQuantity) {
        if ( foodItemQuantity == null ) {
            return null;
        }

        FoodItemQuantityDtoBuilder foodItemQuantityDto = FoodItemQuantityDto.builder();

        foodItemQuantityDto.foodItemId( foodItemQuantity.getFoodItemId() );
        foodItemQuantityDto.quantity( foodItemQuantity.getQuantity() );

        return foodItemQuantityDto.build();
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

        FoodItemQuantityBuilder foodItemQuantity = FoodItemQuantity.builder();

        foodItemQuantity.foodItemId( foodItemQuantityDto.getFoodItemId() );
        foodItemQuantity.quantity( foodItemQuantityDto.getQuantity() );

        return foodItemQuantity.build();
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
