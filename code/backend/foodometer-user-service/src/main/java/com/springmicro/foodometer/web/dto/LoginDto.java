package com.springmicro.foodometer.web.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoginDto {
    @NotNull
    private String username;
    private String password;
}
