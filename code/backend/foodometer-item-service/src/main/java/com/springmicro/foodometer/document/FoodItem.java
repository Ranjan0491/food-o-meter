package com.springmicro.foodometer.document;

import com.springmicro.foodometer.constants.FoodItemCategoryEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "FoodItem")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FoodItem {

    @Id
    private String id;
    private FoodItemCategoryEnum category;
    private String itemName;
    private Double itemPrice;
}
