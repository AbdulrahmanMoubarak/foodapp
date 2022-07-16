package com.training.foodapp.repositories;

import com.training.foodapp.models.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RecipeRepository extends JpaRepository<Recipe, Integer> {
    List<Recipe> findBySearchIng(String search_ing);
}
