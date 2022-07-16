package com.training.foodapp.models;

import java.util.List;

public class IngredientSearchResponse {
    private List<Ingredient> results;

    public List<Ingredient> getResults() {
        return results;
    }

    public void setResults(List<Ingredient> results) {
        this.results = results;
    }
}
