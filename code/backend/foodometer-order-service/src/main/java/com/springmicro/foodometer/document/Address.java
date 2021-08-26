package com.springmicro.foodometer.document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "Address")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Address {
    @Id
    private String id;
    private String line1;
    private String line2;
    private String line3;
    private String state;
    private String city;
    private String pinCode;
}
