package com.example.WebAppApi.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@Configuration
public class apiConfig {

    @Bean
    public OpenAPI openApi() {

        return new OpenAPI()
                .info(new Info()
                        .title("Title")
                        .description("AD Project Web API")
                        .version("v1.0")
                        .contact(new Contact()
                                .name("ADProject")
                                .url("http://localhost:8081")
                                .email("ishikyosanrtn@gmail.com"))
                        .termsOfService("TOC")
                        .license(new License().name("License").url("#")));
    }

}
