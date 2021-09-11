package com.springmicro.foodometer.document;

import com.springmicro.foodometer.constants.FoodOrderStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.sql.Timestamp;
import java.util.List;

@Data
@Document("FoodOrder")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FoodOrder {
    private String id;
    private List<FoodItemQuantity> foodItems;
    private String customerId;
    private String customerAddressId;
    private Timestamp orderTimestamp;
    private Double orderAmount;
    private FoodOrderStatus orderStatus;
    private Double discount;
    private Double discountedAmount;
}
