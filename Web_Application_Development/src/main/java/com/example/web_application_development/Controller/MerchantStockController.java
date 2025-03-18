package com.example.web_application_development.Controller;

import com.example.web_application_development.Api.ApiResponse;
import com.example.web_application_development.Model.MerchantStock;
import com.example.web_application_development.Srevice.MerchantStockService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/merchantStocks")
@RequiredArgsConstructor
public class MerchantStockController {


    private final MerchantStockService merchantStockService;

    @GetMapping("/get")
    public ResponseEntity getAllMerchantStock() {
        ArrayList<MerchantStock> merchantStocks = merchantStockService.getAllMerchantStock();
        return ResponseEntity.status(200).body(merchantStocks);
    }


    @PostMapping("/add")
    public ResponseEntity addMerchantStock(@Valid @RequestBody MerchantStock merchantStock, Errors errors) {
        if (errors.hasErrors()) {
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
        }
        boolean isAddUser = merchantStockService.addMerchantStock(merchantStock);
        if (isAddUser) {
            return ResponseEntity.status(200).body(new ApiResponse("MerchantStock is added"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("MerchantStock with id already exists!"));
    }


    @PutMapping("/update/{id}")
    public ResponseEntity updateMerchantStock(@PathVariable String id, @Valid @RequestBody MerchantStock merchantStock, Errors errors) {
        if (errors.hasErrors()) {
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
        }
        boolean isUpdate = merchantStockService.updateMerchantStock(id, merchantStock);
        if (isUpdate) {
            return ResponseEntity.status(200).body(new ApiResponse("MerchantStock is updated"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("MerchantStock not found!"));
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteMerchantStock(@PathVariable String id) {
        boolean isDelete = merchantStockService.deleteMerchantStock(id);
        if (isDelete) {
            return ResponseEntity.status(200).body(new ApiResponse("MerchantStock is deleted"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("not found"));
    }



    @PutMapping("/stock/{productId}/{merchantId}/{amount}")
    public ResponseEntity addStock(@PathVariable String  productId,@PathVariable String merchantId,@PathVariable int amount,@Valid @RequestBody MerchantStock merchantStock){
        if(amount<0){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse("amount must be greater than zero"));
        }
        boolean isAddStock= merchantStockService.addStock(merchantStock.getProductid(), merchantStock.getMerchantid(), amount);

        if(isAddStock){
            return ResponseEntity.status(200).body(new ApiResponse("Stock is updated"));
        }

        return ResponseEntity.status(400).body(new ApiResponse("stock not found"));
    }



}
