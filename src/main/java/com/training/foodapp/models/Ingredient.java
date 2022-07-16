package com.training.foodapp.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Ingredients")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Ingredient {
    @Id
    private int id;
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
