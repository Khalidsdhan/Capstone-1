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



    // خصم لمن تعدت فاتورته 200 دولار
    public String applyDiscount(String userId, double totalAmount,String productID) {
        for (Product product : productService.getAllProduct()) {
            if (product.getId().equals(productID))
                for (User user : users) {
                    if (user.getId().equals(userId)) {
                        if (totalAmount > 200) {
                            totalAmount *= 0.8;
                            double newprice = product.getPrice() - totalAmount;
                            return "Discount applied! New total: $" + newprice;
                        }
                        return "No discount applied. Total remains: $" + totalAmount;
                    }
                }
            return "User not found";
        }
        return "product not found";

    }


    //  هدية لمن اشترى أكثر من 3 مرات
    public String checkGiftEligibility(String userId, int purchaseCount,String productID) {
        for (Product product : productService.getAllProduct()) {
            if (product.getId().equals(productID))
                for (User user : users) {
                    if (user.getId().equals(userId)) {
                        if (purchaseCount > 3) {
                            return "Congratulations! You are eligible for a free gift!";
                        }
                        return "No gift available. Purchase more to unlock rewards!";
                    }
                }
            return "User not found";
        }
        return "product not found";
    }





}
