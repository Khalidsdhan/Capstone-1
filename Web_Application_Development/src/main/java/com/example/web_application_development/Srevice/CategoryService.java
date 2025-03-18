package com.example.web_application_development.Srevice;


import com.example.web_application_development.Model.Category;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CategoryService {

    ArrayList<Category> categories  =new ArrayList<>();

    public ArrayList<Category> getAllCategories(){
        return categories;
    }


    public boolean addCategory(Category category){
        for (Category category1:categories){
            if(category.getId().equals(category1.getId())){
                return false;
            }
        }
        categories.add(category);
        return true;
    }

    public boolean updateCategory(String id,Category category){
        for(int i=0; i<categories.size();i++){
            if(categories.get(i).getId().equals(id)){
                categories.set(i,category);
                return true;
            }
        }
        return false;
    }



    public boolean deleteCategory(String id){
        for (Category category:categories){
            if(category.getId().equals(id)){
                categories.remove(category);
                return true;
            }
        }
        return false;
    }

}
