package com.springmicro.foodometer.web.mapper;

import com.springmicro.foodometer.document.Address;
import com.springmicro.foodometer.web.dto.AddressDto;
import org.mapstruct.Mapper;

@Mapper
public interface AddressMapper {
    AddressDto addressToAddressDto(Address address);
    Address addressDtoToAddress(AddressDto addressDto);
}
