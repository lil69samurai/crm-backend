package com.eichi.customer_api;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {
    @Bean
    public OpenAPI customerApiOpenAPI(){
        return new OpenAPI()
                .info(new Info()
                        .title("CRM Backend API")
                        .description("A simplified CRM backend build with Spring Boot")
                        .version("1.0.0"));
    }
}
