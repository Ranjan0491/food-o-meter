package com.springmicro.foodometer.web.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomerDto {
    private String id;
    private String firstName;
    private String lastName;
    private String phone;
    @Email
    private String email;
    private List<AddressDto> addresses;
}
