package com.example.web_application_development.Srevice;

import com.example.web_application_development.Model.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class UserService {

    private final ProductService productService;
    private final MerchantService merchantService;
    private final MerchantStockService merchantStockService;


    ArrayList<User> users =new ArrayList<>();


    public ArrayList<User> getAllUser(){
        return users;
    }

    public boolean addUser( User user){
        for (User user1:users){
            if(user1.getId().equals(user.getId())){
                return false;
            }
        }
        users.add(user);
        return true;
    }

    public boolean updateUser(String id,User user){
        for(int i=0; i<users.size();i++){
            if(users.get(i).getId().equals(id)){
                users.set(i,user);
                return true;
            }
        }
        return false;
    }


    public boolean deleteUser(String id){
        for (User user1:users){
            if(user1.getId().equals(id)){
                users.remove(user1);
                return true;
            }
        }
        return false;
    }



    public String buyProduct(String productID, String userID, String merchantID){
       for (Product product:productService.getAllProduct()) {
           if (product.getId().equals(productID)) {
                       for (Merchant merchant: merchantService.getAllMerchant()){
                           if(merchant.getId().equals(merchantID)){
                               for(MerchantStock merchantStock: merchantStockService.getAllMerchantStock()){
                                   if(merchantStock.getId().equals(productID)){
                                       for (User user1 : users) {
                                           if(user1.getId().equals(userID)) {
                                               if (user1.getBalance() >= product.getPrice()){
                                                   if (merchantStock.getStock() > 0){
                                                       user1.setBalance(user1.getBalance() - product.getPrice());
                                                        merchantStock.setStock(merchantStock.getStock() - 1);
                                                        return "Product buy seccessfully";
                                                   }
                                               }
                                           }
                               }
                           }
                       }
                   }
                           return "User not found";
               }
               return "merchantStock not found";

           }
           return "Merchant not found";
       }
       return "Product not found";
    }



   //Frist extra point Discount for user wehn use the copon
    public String applyDiscount(String userId, String copon,String productID) {
        for (Product product : productService.getAllProduct()) {
            if (product.getId().equals(productID))
                for (User user : users) {
                    if (user.getId().equals(userId)) {
                        if (copon.equalsIgnoreCase("SS1")) {
                            double discount = product.getPrice() * 0.20;
                            double newPrice = product.getPrice() - discount;
                            return "Discount applied! New total: $" + newPrice;
                        }
                        return "No discount applied. Total remains: $" +product.getPrice();
                    }
                }
            return "User not found";
        }
        return "product not found";

    }

    //Second extra point get Available Products 
    public ArrayList<Product> getAvailableProducts(String userId) {
        
        for (User user : users) {
            if (user.getId().equals(userId)) {
                ArrayList<Product> availableProducts = new ArrayList<>();
                
                for (Product product : productService.getAllProduct()) {
                    
                    if (user.getBalance() >= product.getPrice()) {
                        availableProducts.add(product);
                    }
                }
                 
                return availableProducts;
            }
        }
        return new ArrayList<>();   
    }


//Third extra point extra point add Balance
    public String addBalance(String userId, double amount) {
        if (amount <= 0) {
            return "The amount must be greater than zero";
        }

        for (User user : users) {
            if (user.getId().equals(userId)) {
                user.setBalance(user.getBalance() + amount);
                return "Successfully added " + amount + " SAR to your balance. Your current balance: " + user.getBalance();
            }
        }

        return "User not found";
    }





}
