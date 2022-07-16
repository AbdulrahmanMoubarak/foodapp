package com.training.foodapp.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
@Table(name = "recipes")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Recipe{
    @Id
    private int id;
    private String title;
    private String image;
    private String searchIng;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getSearchIng() {
        return searchIng;
    }

    public void setSearchIng(String searchIng) {
        this.searchIng = searchIng;
    }
}
