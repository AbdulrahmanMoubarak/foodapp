package com.training.foodapp.config;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.TestPropertySource;
import org.springframework.web.client.RestTemplate;

@TestConfiguration
@TestPropertySource
public class TestAppConfig {
    @Bean
    public TestRestTemplate testRestTemplate() {
        return new TestRestTemplate();
    }
}
