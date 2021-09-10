package com.springmicro.foodometer.web.dto.event;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ValidateOrderResult {
    private String orderId;
    private Boolean isValidOrder;
    private Boolean isValidPayment;
}
