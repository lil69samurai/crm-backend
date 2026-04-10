package com.eichi.customer_api;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {
    @Bean
    public OpenAPI customerApiOpenAPI(){
        final String securitySchemeName = "bearerAuth";
        return new OpenAPI()
                // 🔥 強制指定：雲端環境使用 HTTPS 網址 (解決 CORS 與 Failed to fetch 錯誤)
                .addServersItem(new Server().url("https://crm-backend-production-c4a7.up.railway.app").description("Production Server"))
                .addServersItem(new Server().url("/"))
                .info(new Info()
                        .title("CRM Backend API")
                        .description("A simplified CRM backend build with Spring Boot")
                        .version("1.0.0"))
                        .addSecurityItem(new SecurityRequirement().addList(securitySchemeName))
                        .components(new Components()
                        .addSecuritySchemes(securitySchemeName,
                                new SecurityScheme()
                                        .name(securitySchemeName)
                                        .type(SecurityScheme.Type.HTTP)
                                        .scheme("bearer")
                                        .bearerFormat("JWT")));
    }
}
