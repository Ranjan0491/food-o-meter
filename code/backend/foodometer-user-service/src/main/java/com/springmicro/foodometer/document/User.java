package com.springmicro.foodometer.document;

import com.springmicro.foodometer.constants.UserRole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.Email;
import java.time.LocalDate;
import java.util.List;

@Data
@Document(collection = "User")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {
    @Id
    private String id;
    private String firstName;
    private String lastName;
    private String phone;
    @Email
    private String email;
    private String dob;
    private List<Address> addresses;
    private String password;
    private UserRole userRole;
}
