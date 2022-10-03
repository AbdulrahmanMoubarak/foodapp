package com.training.foodapp.config;

import com.training.foodapp.services.RecipeService;
import org.springframework.aop.scope.DefaultScopedObject;
import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Configuration()
public class AppConfig {
    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }
}
