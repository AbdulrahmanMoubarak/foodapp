package com.training.foodapp;

import com.training.foodapp.models.Recipe;
import com.training.foodapp.services.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class FoodappApplication {


	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(FoodappApplication.class, args);
	}


}
