package com.example.web_application_development.Model;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Category {


    @Size(max = 3)
    @NotEmpty(message = "id must be not empty")
    private String id;


    @NotEmpty(message = "name must be not empty")
    @Size(min = 3,max = 10)
    private String name;


}
