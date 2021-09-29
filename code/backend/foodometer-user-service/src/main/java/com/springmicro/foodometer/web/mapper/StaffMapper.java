package com.springmicro.foodometer.web.mapper;

import com.springmicro.foodometer.document.User;
import com.springmicro.foodometer.web.dto.StaffDto;
import org.mapstruct.Mapper;

@Mapper
public interface StaffMapper {
    StaffDto userToStaffDto(User user);
}
