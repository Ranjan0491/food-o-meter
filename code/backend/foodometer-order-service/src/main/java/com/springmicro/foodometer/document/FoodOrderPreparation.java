package com.springmicro.foodometer.document;

import com.springmicro.foodometer.constants.FoodOrderStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Document("FoodOrderPreparation")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FoodOrderPreparation {
    private String id;
    private String foodOrderId;
    private String staffId;
}
