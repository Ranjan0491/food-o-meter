package com.springmicro.foodometer.web.dto;

import com.springmicro.foodometer.constants.UserRole;
import com.springmicro.foodometer.constants.UserStatus;
import com.springmicro.foodometer.document.Address;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StaffDto {
    private String id;
    @NotNull
    private String firstName;
    @NotNull
    private String lastName;
    @NotNull
    private String phone;
    private String email;
    private String dob;
    private List<Address> addresses;
    private UserRole userRole;

    private UserStatus status;
}
