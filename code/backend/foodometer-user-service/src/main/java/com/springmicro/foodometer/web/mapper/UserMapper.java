package com.springmicro.foodometer.web.mapper;

import com.springmicro.foodometer.document.User;
import com.springmicro.foodometer.web.dto.UserDto;
import org.mapstruct.Mapper;

@Mapper(uses = {AddressMapper.class})
public interface UserMapper {
    UserDto userToUserDto(User user);
    User userDtoToUser(UserDto userDto);
}
