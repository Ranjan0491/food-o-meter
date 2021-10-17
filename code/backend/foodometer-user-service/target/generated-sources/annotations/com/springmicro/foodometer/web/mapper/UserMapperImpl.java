package com.springmicro.foodometer.web.mapper;

import com.springmicro.foodometer.document.Address;
import com.springmicro.foodometer.document.User;
import com.springmicro.foodometer.web.dto.AddressDto;
import com.springmicro.foodometer.web.dto.UserDto;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2021-10-17T12:29:49+0530",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 16.0.2 (Oracle Corporation)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Autowired
    private AddressMapper addressMapper;

    @Override
    public UserDto userToUserDto(User user) {
        if ( user == null ) {
            return null;
        }

        UserDto userDto = new UserDto();

        userDto.setId( user.getId() );
        userDto.setFirstName( user.getFirstName() );
        userDto.setLastName( user.getLastName() );
        userDto.setPhone( user.getPhone() );
        userDto.setEmail( user.getEmail() );
        userDto.setDob( user.getDob() );
        userDto.setAddresses( addressListToAddressDtoList( user.getAddresses() ) );
        userDto.setPassword( user.getPassword() );
        userDto.setUserRole( user.getUserRole() );

        return userDto;
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
        user.setDob( userDto.getDob() );
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
