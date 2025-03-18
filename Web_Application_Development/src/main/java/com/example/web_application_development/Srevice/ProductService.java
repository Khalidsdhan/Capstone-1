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
public class ProductService {


    private final CategoryService categoryService;
    private final MerchantStockService merchantStockService;

    ArrayList<Product> products = new ArrayList<>();


    public ArrayList<Product> getAllProduct() {
        return products;
    }


    public boolean addProduct(Product product) {
        for (Category category : categoryService.getAllCategories()) {
            if (category.getId().equals(product.getCategoryID())) {
                for (Product product1 : products) {
                    if (product1.getCategoryID().equals(product.getCategoryID())) {
                        return false;

                    }
                }

            }
        }
        products.add(product);
        return true;
    }


    public boolean updateProduct(String id, Product product) {
        for (Category category : categoryService.getAllCategories()) {
            if (category.getId().equals(product.getCategoryID())) {
                for (Product product1 : products) {
                    if (product1.getCategoryID().equals(product.getCategoryID())) {
                        products.set(products.indexOf(product1), product);
                        return true;
                    }
                }

            }
        }
        return false;
    }


    public boolean deleteProduct(String id) {
        for (Product product : products) {
            if (product.getId().equals(id)) {
                products.remove(product);
                return true;
            }
        }
        return false;
    }


    // تحقق من توفر المنتج
    public boolean isProductAvailable(String productId, String merchantStockID) {
        for (MerchantStock merchantStock : merchantStockService.getAllMerchantStock()) {
            if (merchantStock.getId().equals(merchantStockID)) {
                for (Product product : products) {
                    if (product.getId().equals(productId)) {
                        if(merchantStock.getStock()>0){
                            return true;
                        }
                    }
                }
                return false;
            }
        }
        return false;
    }


    public ArrayList<Product> searchProduct(String keyword) {
        ArrayList<Product> searchResults = new ArrayList<>();
        for (Product product : products) {
            if (product.getName().toLowerCase().contains(keyword.toLowerCase())) {
                searchResults.add(product);
            }
        }
        return searchResults;
    }


    public ArrayList<Product> getProductsByCategory(String categoryId) {
        ArrayList<Product> categoryProducts = new ArrayList<>();
        for (Product product : products) {
            if (product.getCategoryID().equals(categoryId)) {
                categoryProducts.add(product);
            }
        }
        return categoryProducts;
    }


    public ArrayList<Product> filterProductsByPriceRange(double minPrice, double maxPrice) {
        ArrayList<Product> filteredProducts = new ArrayList<>();
        for (Product product : products) {
            if (product.getPrice() >= minPrice && product.getPrice() <= maxPrice) {
                filteredProducts.add(product);
            }
        }
        return filteredProducts;
    }



//    //المنتجات الارخص
//    public ArrayList<Product> getCheapProducts() {
//        ArrayList<Product> cheapProducts = new ArrayList<>();
//        for (Product product : products) {
//            if (product.getPrice() < 50) {
//                cheapProducts.add(product);
//            }
//        }
//        return cheapProducts;
//    }
//
////المنتجات الاغلى
//    public ArrayList<Product> getExpensiveProducts() {
//        ArrayList<Product> expensiveProducts = new ArrayList<>();
//        for (Product product : products) {
//            if (product.getPrice() > 200) {
//                expensiveProducts.add(product);
//            }
//        }
//        return expensiveProducts;
//    }



}

