package com.springmicro.foodometer.web.dto;

import jakarta.validation.constraints.NotNull;
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
    @NotNull
    private String line1;
    private String line2;
    private String line3;
    @NotNull
    private String state;
    @NotNull
    private String city;
    @NotNull
    private String pinCode;
}
