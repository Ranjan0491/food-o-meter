package com.springmicro.foodometer.web.mapper;

import com.springmicro.foodometer.document.FoodOrder;
import com.springmicro.foodometer.web.dto.FoodOrderDto;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2021-10-03T19:38:52+0530",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 16.0.2 (Oracle Corporation)"
)
@Component
@Primary
public class FoodOrderMapperImpl extends DetailedFoodOrderMapperDecorator implements FoodOrderMapper {

    @Autowired
    @Qualifier("delegate")
    private FoodOrderMapper delegate;

    @Override
    public FoodOrderDto foodOrderToFoodOrderDto(FoodOrder foodOrder)  {
        return delegate.foodOrderToFoodOrderDto( foodOrder );
    }

    @Override
    public FoodOrder foodOrderDtoToFoodOrder(FoodOrderDto foodOrderDto)  {
        return delegate.foodOrderDtoToFoodOrder( foodOrderDto );
    }
}
