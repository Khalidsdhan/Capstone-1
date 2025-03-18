package com.example.web_application_development.Controller;

import com.example.web_application_development.Api.ApiResponse;
import com.example.web_application_development.Model.Merchant;
import com.example.web_application_development.Model.Product;
import com.example.web_application_development.Srevice.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;


    @GetMapping("/get")
    public ResponseEntity getAllMerchant() {
        ArrayList<Product> products = productService.getAllProduct();
        return ResponseEntity.status(200).body(products);
    }


    @PostMapping("/add")
    public ResponseEntity addProduct(@Valid @RequestBody Product product, Errors errors) {
        if (errors.hasErrors()) {
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
        }
        boolean isAddUser = productService.addProduct(product);
        if (isAddUser) {
            return ResponseEntity.status(200).body(new ApiResponse("Product is added"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("Product with id already exists!"));
    }


    @PutMapping("/update/{id}")
    public ResponseEntity updateProduct(@PathVariable String id,@Valid @RequestBody Product product , Errors errors){
        if(errors.hasErrors()){
            String message= errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
        }
        boolean isUpdate = productService.updateProduct(id, product);
        if(isUpdate){
            return ResponseEntity.status(200).body(new ApiResponse("Product is updated"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("Product not found!"));
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteProduct(@PathVariable String id){
        boolean isDelete= productService.deleteProduct(id);
        if(isDelete){
            return ResponseEntity.status(200).body(new ApiResponse("Product is deleted"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("not found"));
    }


    @GetMapping("/out-of-stock/{productId}/{merchantStockID}")
    public ResponseEntity isProductAvailable(@PathVariable String productId, @PathVariable String merchantStockID) {
        boolean isOutOfStock = productService.isProductAvailable(productId,merchantStockID);
        if (isOutOfStock) {
            return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("Product is still available."));
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse("Product is out of stock."));
    }

    @GetMapping("/search")
    public ResponseEntity searchProducts(@RequestParam String keyword) {
        ArrayList<Product> products = productService.searchProduct(keyword);
        if (!products.isEmpty()) {
            return ResponseEntity.status(HttpStatus.OK).body(products);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse("products not found."));
    }


    @GetMapping("/category/{categoryId}")
    public ResponseEntity getProductsByCategory(@PathVariable String categoryId) {
        ArrayList<Product> products = productService.getProductsByCategory(categoryId);
        if (!products.isEmpty()) {
            return ResponseEntity.status(HttpStatus.OK).body(products);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse("products not found in this category."));
    }


//    @GetMapping("/cheap")
//    public ResponseEntity getCheapProducts() {
//        ArrayList<Product> cheapProducts = productService.getCheapProducts();
//        return ResponseEntity.status(200).body(cheapProducts);
//    }
//
//    @GetMapping("/expensive")
//    public ResponseEntity getExpensiveProducts() {
//        ArrayList<Product> expensiveProducts = productService.getExpensiveProducts();
//        return ResponseEntity.status(200).body(expensiveProducts);
//    }

}
