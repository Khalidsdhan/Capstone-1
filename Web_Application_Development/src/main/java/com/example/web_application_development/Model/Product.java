package com.example.web_application_development.Model;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;

@Data
@AllArgsConstructor
public class Product {

    @Size(max = 10)
    @NotEmpty(message = "id must be not empty")
    private String id;

    @Size(min = 3,max = 9)
    @NotEmpty(message = "name must be not empty")
    private String name;


    @Positive
    @NotNull
    private double price;


    @NotEmpty(message = "category ID must be not empty")
    private String categoryID;







}
