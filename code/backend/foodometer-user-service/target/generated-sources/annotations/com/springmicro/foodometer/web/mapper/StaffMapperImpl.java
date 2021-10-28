package com.springmicro.foodometer.web.mapper;

import com.springmicro.foodometer.document.Address;
import com.springmicro.foodometer.document.User;
import com.springmicro.foodometer.web.dto.StaffDto;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2021-10-24T12:36:42+0530",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 16.0.2 (Oracle Corporation)"
)
@Component
public class StaffMapperImpl implements StaffMapper {

    @Override
    public StaffDto userToStaffDto(User user) {
        if ( user == null ) {
            return null;
        }

        StaffDto staffDto = new StaffDto();

        staffDto.setId( user.getId() );
        staffDto.setFirstName( user.getFirstName() );
        staffDto.setLastName( user.getLastName() );
        staffDto.setPhone( user.getPhone() );
        staffDto.setEmail( user.getEmail() );
        staffDto.setDob( user.getDob() );
        List<Address> list = user.getAddresses();
        if ( list != null ) {
            staffDto.setAddresses( new ArrayList<Address>( list ) );
        }
        staffDto.setUserRole( user.getUserRole() );
        staffDto.setStatus( user.getStatus() );

        return staffDto;
    }
}
