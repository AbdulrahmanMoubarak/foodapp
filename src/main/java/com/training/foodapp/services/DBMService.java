package com.training.foodapp.services;

import com.training.foodapp.models.Recipe;
import com.training.foodapp.repositories.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DBMService {

    @Autowired
    RecipeRepository recipeDao;

    public void saveResponse(List<Recipe> recipes) {
        for (Recipe rec : recipes) {
            recipeDao.save(rec);
        }
    }

    public List<Recipe> getRecipesByIngredient(String ingredient){
        try {
            return recipeDao.findBySearchIng(ingredient);
        }catch (Exception e){
            return new ArrayList<Recipe>();
        }
    }
}
