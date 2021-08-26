package com.springmicro.foodometer.web.mapper;

import com.springmicro.foodometer.document.Customer;
import com.springmicro.foodometer.web.dto.CustomerDto;
import org.mapstruct.Mapper;

@Mapper(uses = {AddressMapper.class})
public interface CustomerMapper {
    CustomerDto customerToCustomerDto(Customer customer);
    Customer customerDtoToCustomer(CustomerDto customerDto);
}
