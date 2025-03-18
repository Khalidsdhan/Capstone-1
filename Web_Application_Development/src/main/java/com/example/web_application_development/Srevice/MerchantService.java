package com.example.web_application_development.Srevice;


import com.example.web_application_development.Model.Merchant;
import com.example.web_application_development.Model.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class MerchantService {

    ArrayList<Merchant> merchants   =new ArrayList<>();


    public ArrayList<Merchant> getAllMerchant(){
        return merchants;
    }



    public boolean addMerchant(Merchant merchant){
        for (Merchant merchant1:merchants){
            if(merchant1.getId().equals(merchant.getId())){
                return false;
            }
        }
        merchants.add(merchant);
        return true;
    }

    public boolean updateMerchant(String id,Merchant merchant){
        for(int i=0; i<merchants.size();i++){
            if(merchants.get(i).getId().equals(id)){
                merchants.set(i,merchant);
                return true;
            }
        }
        return false;
    }


    public boolean deletMerchant(String id){
        for (Merchant merchant:merchants){
            if(merchant.getId().equals(id)){
                merchants.remove(merchant);
                return true;
            }
        }
        return false;
    }

}
