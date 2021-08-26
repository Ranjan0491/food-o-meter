package com.springmicro.foodometer.web.mapper;

import com.springmicro.foodometer.document.Address;
import com.springmicro.foodometer.web.dto.AddressDto;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2021-08-26T20:47:41+0530",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 15.0.2 (Oracle Corporation)"
)
@Component
public class AddressMapperImpl implements AddressMapper {

    @Override
    public AddressDto addressToAddressDto(Address address) {
        if ( address == null ) {
            return null;
        }

        AddressDto addressDto = new AddressDto();

        addressDto.setId( address.getId() );
        addressDto.setLine1( address.getLine1() );
        addressDto.setLine2( address.getLine2() );
        addressDto.setLine3( address.getLine3() );
        addressDto.setState( address.getState() );
        addressDto.setCity( address.getCity() );
        addressDto.setPinCode( address.getPinCode() );

        return addressDto;
    }

    @Override
    public Address addressDtoToAddress(AddressDto addressDto) {
        if ( addressDto == null ) {
            return null;
        }

        Address address = new Address();

        address.setId( addressDto.getId() );
        address.setLine1( addressDto.getLine1() );
        address.setLine2( addressDto.getLine2() );
        address.setLine3( addressDto.getLine3() );
        address.setState( addressDto.getState() );
        address.setCity( addressDto.getCity() );
        address.setPinCode( addressDto.getPinCode() );

        return address;
    }
}
