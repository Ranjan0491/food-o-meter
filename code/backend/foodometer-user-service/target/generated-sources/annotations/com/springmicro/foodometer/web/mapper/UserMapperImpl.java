package com.springmicro.foodometer.web.mapper;

import com.springmicro.foodometer.document.Address;
import com.springmicro.foodometer.document.User;
import com.springmicro.foodometer.web.dto.AddressDto;
import com.springmicro.foodometer.web.dto.UserDto;
import com.springmicro.foodometer.web.dto.UserDto.UserDtoBuilder;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2021-10-19T09:53:15+0530",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 16.0.2 (Oracle Corporation)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Autowired
    private AddressMapper addressMapper;
    @Autowired
    private DateMapper dateMapper;

    @Override
    public UserDto userToUserDto(User user) {
        if ( user == null ) {
            return null;
        }

        UserDtoBuilder userDto = UserDto.builder();

        userDto.id( user.getId() );
        userDto.firstName( user.getFirstName() );
        userDto.lastName( user.getLastName() );
        userDto.phone( user.getPhone() );
        userDto.email( user.getEmail() );
        userDto.dob( dateMapper.asLocalDate( user.getDob() ) );
        userDto.addresses( addressListToAddressDtoList( user.getAddresses() ) );
        userDto.password( user.getPassword() );
        userDto.userRole( user.getUserRole() );

        return userDto.build();
    }

    @Override
    public User userDtoToUser(UserDto userDto) {
        if ( userDto == null ) {
            return null;
        }

        User user = new User();

        user.setId( userDto.getId() );
        user.setFirstName( userDto.getFirstName() );
        user.setLastName( userDto.getLastName() );
        user.setPhone( userDto.getPhone() );
        user.setEmail( userDto.getEmail() );
        user.setDob( dateMapper.asString( userDto.getDob() ) );
        user.setAddresses( addressDtoListToAddressList( userDto.getAddresses() ) );
        user.setPassword( userDto.getPassword() );
        user.setUserRole( userDto.getUserRole() );

        return user;
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
