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
public class PasswordUpdateDto {
    @NotNull
    private String currentPassword;
    @NotNull
    private String newPassword;
}
