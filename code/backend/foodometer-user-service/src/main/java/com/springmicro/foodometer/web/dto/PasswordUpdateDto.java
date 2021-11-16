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
public class PasswordUpdateDto {
    @NotNull
    private String currentPassword;
    @NotNull
    private String newPassword;
}
