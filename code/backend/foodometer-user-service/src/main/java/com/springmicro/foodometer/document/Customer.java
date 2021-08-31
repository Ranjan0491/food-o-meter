package com.springmicro.foodometer.document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.Email;
import java.sql.Date;
import java.util.List;

@Data
@Document(collection = "Customer")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Customer {
    @Id
    private String id;
    private String firstName;
    private String lastName;
    private String phone;
    @Email
    private String email;
    private Date dob;
    @DBRef(lazy = true)
    private List<Address> addresses;
    private String password;
    private List<String> roles;
}
