package com.springmicro.foodometer.document;

import com.springmicro.foodometer.constants.FoodOrderStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document("FoodOrderDelivery")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FoodOrderDelivery {
    private String id;
    private String foodOrderId;
    private String staffId;
    private FoodOrderStatus foodOrderStatus;
}
