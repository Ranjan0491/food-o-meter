package com.springmicro.foodometer.web.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.springmicro.foodometer.constants.UserRole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto {
    private String id;
    @NotNull
    private String firstName;
    @NotNull
    private String lastName;
    @NotNull
    private String phone;
    @Email
    @NotNull
    private String email;
    @NotNull
    @JsonFormat(pattern="M/d/yyyy", shape=JsonFormat.Shape.STRING)
    private LocalDate dob;
    private List<AddressDto> addresses;
    @JsonIgnore
    private String password;
    private UserRole userRole;
}
