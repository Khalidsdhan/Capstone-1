package com.example.web_application_development.Model;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MerchantStock {

    @NotEmpty(message = "id must be not empty")
    @Size(min =10)
    private String id;

    @NotEmpty(message = "productid must be not empty")
    private String productid;

    @NotEmpty(message = " merchantid must be not empty")
    private String merchantid;

    @NotNull(message = "Stock must not be null")
    @Min(value = 11, message = "Stock must be more than 10 at start")
    private Integer stock;
}
