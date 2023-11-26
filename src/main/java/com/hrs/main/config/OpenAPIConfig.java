package com.hrs.main.config;

import io.swagger.v3.oas.models.Components;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenAPIConfig {

	@Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .components(new Components())
                .info(new Info().title("Logistic-Service")
                        .description("Logistic Product service APIs")
                        .version("v1.0.0")
                        .license(new License()
                                .name("Proprietary - ©")
                                .url("")));
        
	}
	
	
}

