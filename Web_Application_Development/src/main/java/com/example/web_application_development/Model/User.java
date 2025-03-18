package com.example.web_application_development.Model;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.hibernate.validator.constraints.Length;


@Data
@AllArgsConstructor
public class User {

//id (must not be empty).
//• username (must not be empty, have to be more than 5 length long).
//• password (must not be empty, have to be more than 6 length long, must have
//characters and digits).
//• email (must not be empty, must be valid email).
//• role (must not be empty, have to be in ( “Admin”,”Customer”)).
//• balance (must not be empty, have to be positive)


    @NotEmpty(message = "id must be not empty")
    @Size(min = 5)
    private String id;

    @NotEmpty(message = "name must be not empty")
    @Size(min = 5)
    private String username;

    @NotEmpty(message = "passowrd must be not empty")
    @Length(min = 6)
    @Pattern(regexp="^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$")
    private String password;
    @NotEmpty(message = "email must be not empty")
    @Email
    private String email;
    @NotEmpty(message = "role must be not empty")
    @Pattern(regexp = ("Admin|Customer"), message = "Role must be either 'Admin' or 'Customer'")
    private String role;
    @NotNull
    @Positive
    private double balance;


    @Positive
    private double totalPurchases = 0;
    @Positive
    private int purchaseCount = 0;

}
