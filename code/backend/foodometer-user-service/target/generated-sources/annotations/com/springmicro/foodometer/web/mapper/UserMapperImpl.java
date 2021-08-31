package com.springmicro.foodometer.web.mapper;

import com.springmicro.foodometer.constants.UserRole;
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
    date = "2021-08-31T17:34:13+0530",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 15.0.2 (Oracle Corporation)"
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

        UserDto userDto = new UserDto();

        userDto.setId( user.getId() );
        userDto.setFirstName( user.getFirstName() );
        userDto.setLastName( user.getLastName() );
        userDto.setPhone( user.getPhone() );
        userDto.setEmail( user.getEmail() );
        userDto.setDob( dateMapper.asLocalDate( user.getDob() ) );
        userDto.setAddresses( addressListToAddressDtoList( user.getAddresses() ) );
        userDto.setPassword( user.getPassword() );
        List<UserRole> list1 = user.getUserRoles();
        if ( list1 != null ) {
            userDto.setUserRoles( new ArrayList<UserRole>( list1 ) );
        }

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
        user.setDob( dateMapper.asSqlDate( userDto.getDob() ) );
        user.setAddresses( addressDtoListToAddressList( userDto.getAddresses() ) );
        user.setPassword( userDto.getPassword() );
        List<UserRole> list1 = userDto.getUserRoles();
        if ( list1 != null ) {
            user.setUserRoles( new ArrayList<UserRole>( list1 ) );
        }

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
