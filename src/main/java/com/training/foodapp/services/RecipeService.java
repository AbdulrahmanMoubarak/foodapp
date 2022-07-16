package com.training.foodapp.services;

import com.training.foodapp.models.Ingredient;
import com.training.foodapp.models.IngredientSearchResponse;
import com.training.foodapp.models.Recipe;
import com.training.foodapp.models.RecipeSteps;
import com.training.foodapp.services.util.ApiRequestBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class RecipeService {
    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private DBMService databaseService;

    @Value("${api_key}")
    private String API_KEY_PARAM;
    @Value("${api_base_url}")
    private String BASE_URL;

    public List<Recipe> getRecipesByIngredientName(String ing_name) {
        List<Recipe> localRecipes = databaseService.getRecipesByIngredient(ing_name);
        if (localRecipes.size() == 0) {
            System.out.println("From API");
            String url = new ApiRequestBuilder(BASE_URL)
                    .addPath("recipes")
                    .addPath("findByIngredients")
                    .addQueryParam("number", "5")
                    .addQueryParam("ingredients", ing_name)
                    .addQueryParam("apiKey", API_KEY_PARAM)
                    .build();

            Recipe[] resp = restTemplate.getForObject(url, Recipe[].class);
            assert resp != null;
            for (Recipe rec : resp) {
                rec.setSearchIng(ing_name);
            }
            databaseService.saveResponse(List.of(resp));
            return List.of(resp);
        } else{
            System.out.println("From Database");
            return localRecipes;
        }
    }

    public List<Ingredient> searchIngredients(String query) {
        String url = new ApiRequestBuilder(BASE_URL)
                .addPath("food")
                .addPath("ingredients")
                .addPath("search")
                .addQueryParam("query", query)
                .addQueryParam("apiKey", API_KEY_PARAM)
                .build();

        IngredientSearchResponse resp = restTemplate.getForObject(url, IngredientSearchResponse.class);
        assert resp != null;
        return resp.getResults();
    }

    public RecipeSteps[] getRecipeSteps(int recipeId) {
        String url = new ApiRequestBuilder(BASE_URL)
                .addPath("recipes")
                .addPath(Integer.toString(recipeId))
                .addPath("analyzedInstructions")
                .addQueryParam("apiKey", API_KEY_PARAM)
                .build();
        RecipeSteps[] resp = restTemplate.getForObject(url, RecipeSteps[].class);
        assert resp != null;
        resp[0].setRecipe_id(recipeId);
        return resp;
    }

    public String getRecipeName(int rid){
        String url = new ApiRequestBuilder(BASE_URL)
                .addPath("recipes")
                .addPath(Integer.toString(rid))
                .addPath("information")
                .addQueryParam("apiKey", API_KEY_PARAM)
                .build();

        Recipe resp = restTemplate.getForObject(url, Recipe.class);
        assert resp != null;
        return resp.getTitle();
    }
    //647524
}
