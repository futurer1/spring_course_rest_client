package com.mikhail.spring.rest.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
@ComponentScan("com.mikhail.spring.rest")
public class MyConfig {

    @Bean
    public RestTemplate restTemplate() {
        // бин для запросов к Rest-сервису
        return new RestTemplate();
    }
}
