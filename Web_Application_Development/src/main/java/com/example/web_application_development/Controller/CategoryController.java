package com.example.web_application_development.Controller;

import com.example.web_application_development.Api.ApiResponse;
import com.example.web_application_development.Model.Category;
import com.example.web_application_development.Srevice.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/category")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;


    @GetMapping("/get")
    public ResponseEntity getAllCategory() {
        ArrayList<Category> categories = categoryService.getAllCategories();
        return ResponseEntity.status(200).body(categories);
    }


    @PostMapping("/add")
    public ResponseEntity addCategory(@Valid @RequestBody Category category, Errors errors) {
        if (errors.hasErrors()) {
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
        }
        boolean isAddUser = categoryService.addCategory(category);
        if (isAddUser) {
            return ResponseEntity.status(200).body(new ApiResponse("Category is added"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("Category with id already exists!"));
    }


    @PutMapping("/update/{id}")
    public ResponseEntity updateUser(@PathVariable String id,@Valid @RequestBody Category category , Errors errors){
        if(errors.hasErrors()){
            String message= errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
        }
        boolean isUpdate = categoryService.updateCategory(id,category);
        if(isUpdate){
            return ResponseEntity.status(200).body(new ApiResponse("Category is updated"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("Category not found!"));
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteCategory(@PathVariable String id){
        boolean isDelete= categoryService.deleteCategory(id);
        if(isDelete){
            return ResponseEntity.status(200).body(new ApiResponse("Category is deleted"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("not found"));
    }

}
