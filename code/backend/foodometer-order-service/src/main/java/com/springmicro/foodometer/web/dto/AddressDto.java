package com.springmicro.foodometer.web.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AddressDto {
    private String id;
    private String line1;
    private String line2;
    private String line3;
    private String state;
    private String city;
    private String pinCode;
}
