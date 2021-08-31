package com.springmicro.foodometer.web.mapper;

import com.springmicro.foodometer.document.Address;
import com.springmicro.foodometer.document.Customer;
import com.springmicro.foodometer.web.dto.AddressDto;
import com.springmicro.foodometer.web.dto.CustomerDto;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2021-08-31T14:39:38+0530",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 15.0.2 (Oracle Corporation)"
)
@Component
public class CustomerMapperImpl implements CustomerMapper {

    @Autowired
    private AddressMapper addressMapper;
    @Autowired
    private DateMapper dateMapper;

    @Override
    public CustomerDto customerToCustomerDto(Customer customer) {
        if ( customer == null ) {
            return null;
        }

        CustomerDto customerDto = new CustomerDto();

        customerDto.setId( customer.getId() );
        customerDto.setFirstName( customer.getFirstName() );
        customerDto.setLastName( customer.getLastName() );
        customerDto.setPhone( customer.getPhone() );
        customerDto.setEmail( customer.getEmail() );
        customerDto.setDob( dateMapper.asLocalDate( customer.getDob() ) );
        customerDto.setAddresses( addressListToAddressDtoList( customer.getAddresses() ) );
        customerDto.setPassword( customer.getPassword() );
        List<String> list1 = customer.getRoles();
        if ( list1 != null ) {
            customerDto.setRoles( new ArrayList<String>( list1 ) );
        }

        return customerDto;
    }

    @Override
    public Customer customerDtoToCustomer(CustomerDto customerDto) {
        if ( customerDto == null ) {
            return null;
        }

        Customer customer = new Customer();

        customer.setId( customerDto.getId() );
        customer.setFirstName( customerDto.getFirstName() );
        customer.setLastName( customerDto.getLastName() );
        customer.setPhone( customerDto.getPhone() );
        customer.setEmail( customerDto.getEmail() );
        customer.setDob( dateMapper.asSqlDate( customerDto.getDob() ) );
        customer.setAddresses( addressDtoListToAddressList( customerDto.getAddresses() ) );
        customer.setPassword( customerDto.getPassword() );
        List<String> list1 = customerDto.getRoles();
        if ( list1 != null ) {
            customer.setRoles( new ArrayList<String>( list1 ) );
        }

        return customer;
    }

    protected List<AddressDto> addressListToAddressDtoList(List<Address> list) {
        if ( list == null ) {
            return null;
        }

        List<AddressDto> list1 = new ArrayList<AddressDto>( list.size() );
        for ( Address address : list ) {
            list1.add( addressMapper.addressToAddressDto( address ) );
        }

        return list1;
    }

    protected List<Address> addressDtoListToAddressList(List<AddressDto> list) {
        if ( list == null ) {
            return null;
        }

        List<Address> list1 = new ArrayList<Address>( list.size() );
        for ( AddressDto addressDto : list ) {
            list1.add( addressMapper.addressDtoToAddress( addressDto ) );
        }

        return list1;
    }
}
