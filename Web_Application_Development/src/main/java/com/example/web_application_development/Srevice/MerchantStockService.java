package com.example.web_application_development.Srevice;


import com.example.web_application_development.Model.Category;
import com.example.web_application_development.Model.Merchant;
import com.example.web_application_development.Model.MerchantStock;
import com.example.web_application_development.Model.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class MerchantStockService {

//    private final ProductService productService;
    private final MerchantService merchantService;

    ArrayList<MerchantStock> merchantStocks = new ArrayList<>();


    public ArrayList<MerchantStock> getAllMerchantStock() {
        return merchantStocks;
    }


    public boolean addMerchantStock(MerchantStock merchantStock) {
        for (Merchant merchant : merchantService.getAllMerchant()) {
            if (merchant.getId().equals(merchantStock.getMerchantid())) {
                for (MerchantStock merchantStock1 : merchantStocks) {
                    if (merchantStock1.getId().equals(merchantStock.getId())) {
                        return false;

                    }
                }

            }
        }
        merchantStocks.add(merchantStock);
        return true;
    }


    public boolean updateMerchantStock(String id, MerchantStock merchantStock) {
        for (Merchant merchant : merchantService.getAllMerchant()) {
            if (merchant.getId().equals(merchantStock.getMerchantid())) {
                for (MerchantStock merchantStock1 : merchantStocks) {
                    if (merchantStock1.getId().equals(merchantStock.getId())) {
                        merchantStocks.set(merchantStocks.indexOf(merchantStock1), merchantStock);
                        return true;
                    }
                }

            }
        }
        return false;
    }


    public boolean deleteMerchantStock(String id) {
        for (MerchantStock merchantStock : merchantStocks) {
            if (merchantStock.getId().equals(id)) {
                merchantStocks.remove(merchantStock);
                return true;
            }
        }
        return false;
    }


    public boolean addStock(String productId, String merchantId, int amount) {
        if (amount <= 0) {
            return false;
        }

        for (MerchantStock stock : merchantStocks) {
            if (stock.getProductid().equals(productId) && stock.getMerchantid().equals(merchantId)) {
                stock.setStock(stock.getStock() + amount);
                return true;
            }
        }

        return false;
    }







}