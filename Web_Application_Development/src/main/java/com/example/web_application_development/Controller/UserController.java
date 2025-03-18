package com.example.web_application_development.Controller;

import com.example.web_application_development.Api.ApiResponse;
import com.example.web_application_development.Model.User;
import com.example.web_application_development.Srevice.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {


    private final UserService userService;

    @GetMapping("/get")
    public ResponseEntity getAllUser() {
        ArrayList<User> users = userService.getAllUser();
        return ResponseEntity.status(200).body(users);
    }

    @PostMapping("/add")
    public ResponseEntity addUser( @RequestBody @Valid User user, Errors errors) {
        if (errors.hasErrors()) {
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
        }
        boolean isAddUser = userService.addUser(user);
        if (isAddUser) {
            return ResponseEntity.status(200).body(new ApiResponse("user is added"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("user with id already exists!"));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateUser(@PathVariable String id, @Valid @RequestBody User user, Errors errors) {
        if (errors.hasErrors()) {
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
        }
        boolean isUpdateUser = userService.updateUser(id, user);
        if (isUpdateUser) {
            return ResponseEntity.status(200).body(new ApiResponse("user is updated"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("user not found!"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteUser(@PathVariable String id) {
        boolean isDelete = userService.deleteUser(id);
        if (isDelete) {
            return ResponseEntity.status(200).body(new ApiResponse("user is deleted"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("not found"));
    }


    public ResponseEntity buyProduct(@PathVariable String productID, String userID, String merchantID) {

        String message = userService.buyProduct(productID, userID, merchantID);
        if (message.equals("Product buy seccessfully")) {
            return ResponseEntity.status(HttpStatus.OK).body(message);
        } else if ((message.equals("Product not found"))) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
        } else if ((message.equals("Merchant not found"))) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
        } else if ((message.equals("merchantStock not found"))) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse("User not found"));
    }

    @GetMapping("/discount/{userId}/{totalAmount}/{productID}")
    public ResponseEntity applyDiscount(@PathVariable String userId, @PathVariable double totalAmount,@PathVariable String productID) {
        String message = userService.applyDiscount(userId, totalAmount,productID);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse(message));
    }

    @GetMapping("/gift/{userId}/{purchaseCount}/{productID}")
    public ResponseEntity checkGiftEligibility(@PathVariable String userId ,@PathVariable int purchaseCount,@PathVariable String productID) {
        String message = userService.checkGiftEligibility(userId,purchaseCount,productID);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse(message));
    }
}