package com.example.web_application_development.Controller;

import com.example.web_application_development.Api.ApiResponse;
import com.example.web_application_development.Model.Merchant;
import com.example.web_application_development.Srevice.MerchantService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/merchant")
@RequiredArgsConstructor
public class MerchantController {


    private final MerchantService merchantService;



    @GetMapping("/get")
    public ResponseEntity getAllMerchant() {
        ArrayList<Merchant> merchants = merchantService.getAllMerchant();
        return ResponseEntity.status(200).body(merchants);
    }


    @PostMapping("/add")
    public ResponseEntity addMerchant(@Valid @RequestBody Merchant merchant, Errors errors) {
        if (errors.hasErrors()) {
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
        }
        boolean isAddUser = merchantService.addMerchant(merchant);
        if (isAddUser) {
            return ResponseEntity.status(200).body(new ApiResponse("Merchant is added"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("Merchant with id already exists!"));
    }


    @PutMapping("/update/{id}")
    public ResponseEntity updateMerchant(@PathVariable String id,@Valid @RequestBody Merchant merchant , Errors errors){
        if(errors.hasErrors()){
            String message= errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
        }
        boolean isUpdate = merchantService.updateMerchant(id,merchant);
        if(isUpdate){
            return ResponseEntity.status(200).body(new ApiResponse("Merchant is updated"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("Merchant not found!"));
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteMerchant(@PathVariable String id){
        boolean isDelete= merchantService.deletMerchant(id);
        if(isDelete){
            return ResponseEntity.status(200).body(new ApiResponse("Merchant is deleted"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("not found"));
    }



}
