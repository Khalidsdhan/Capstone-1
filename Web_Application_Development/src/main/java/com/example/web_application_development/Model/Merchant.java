package com.example.web_application_development.Model;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Merchant {

    //id (must not be empty).
    //• name (must not be empty, have to be more than 3 length long).

    @NotEmpty(message = "id must be not empty")
    @Size(min = 5)
    private String id;

    @NotEmpty(message = "id must be not empty")
    @Size(min = 3,max = 8)
    private String name;
}
