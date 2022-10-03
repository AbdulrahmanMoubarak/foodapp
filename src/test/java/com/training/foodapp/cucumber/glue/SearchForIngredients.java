package com.training.foodapp.cucumber.glue;

import com.training.foodapp.models.Ingredient;
import io.cucumber.java.Before;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;

import java.util.ArrayList;
import java.util.List;

public class SearchForIngredients {

    @Autowired
    private TestRestTemplate testRestTemplate;

    private List<Ingredient> expected;

    @When("^the user searches for ingredient (.*)$")
    public void whenTheUserSearchesForIngredient(final String ingredient){
        this.expected = List.of(this.testRestTemplate.getForEntity("/ingredients/search?query=" + ingredient, Ingredient[].class).getBody());
    }
    @Then("^all related ingredients are returned$")
    public void thenAllRelatedIngredientsAreReturned(){
        validateIngredientsResult(this.expected);
    }

    private void validateIngredientsResult(List<Ingredient> ingredients){
        Assertions.assertNotEquals(ingredients.size(), 0);
        Assertions.assertEquals(ingredients.get(0).getName(), "meatballs");
    }

    @Before
    public void setup(){
        expected = new ArrayList<>();
    }
}
