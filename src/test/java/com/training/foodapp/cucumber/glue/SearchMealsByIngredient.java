package com.training.foodapp.cucumber.glue;

import com.training.foodapp.models.Recipe;
import com.training.foodapp.repositories.RecipeRepository;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.assertj.core.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;

import java.util.ArrayList;
import java.util.List;

public class SearchMealsByIngredient {
    @Autowired
    private TestRestTemplate testRestTemplate;

    @Autowired
    private RecipeRepository repository;

    private List<Recipe> expectedRecipes;

    @Before
    public void setup() {
        expectedRecipes = new ArrayList<>();
    }

    @When("^the user requests meals that contain (.*)$")
    public void whenTheUserRequestsMealsThatContain(String ingredient) {
        this.expectedRecipes = List.of(testRestTemplate.getForEntity("/ingredients/" + ingredient + "/search", Recipe[].class).getBody());
    }

    @Then("^all meals that contain (.*) are returned$")
    public void thenAllMealsThatContainMeatAreReturned(String ingredient) {
        validateReturnedRecipes(ingredient);
    }

    @And("^the response is stored in the database marked by (.*)$")
    public void andTheResponseIsStoredInTheDatabase(String ingredient) {
        List<Recipe> databaseIngs = repository.findBySearchIng(ingredient);
        Assertions.assertThat(databaseIngs.size()).isGreaterThan(0);
    }

    private void validateReturnedRecipes(String ingredient) {
        for (Recipe recipe :
                expectedRecipes) {
            Assertions.assertThat(recipe.getSearchIng()).isEqualTo(ingredient);
        }
    }
}
