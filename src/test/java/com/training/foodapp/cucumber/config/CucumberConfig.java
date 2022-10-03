package com.training.foodapp.cucumber.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.training.foodapp.models.Ingredient;
import io.cucumber.java.DefaultDataTableCellTransformer;
import io.cucumber.java.DefaultDataTableEntryTransformer;
import io.cucumber.java.DefaultParameterTransformer;
import io.cucumber.java.en.Given;

import java.lang.reflect.Type;
import java.util.List;


public class CucumberConfig {
    private final ObjectMapper objectMapper;

    public CucumberConfig(){
        objectMapper = new ObjectMapper();
    }

    @DefaultDataTableCellTransformer
    @DefaultDataTableEntryTransformer
    @DefaultParameterTransformer
    public Object transform(final Object from, final Type to){
        return objectMapper.convertValue(from, objectMapper.constructType(to));
    }
}
