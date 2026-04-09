package com.eichi.customer_api;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // 1. Close CSRF protect
                .csrf(AbstractHttpConfigurer::disable)

                // 2. Set Session to none（for using JWT）
                .sessionManagement(session ->
                        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))

                // 3. Set the protection for the links
                .authorizeHttpRequests(auth -> auth
                        // Allow visit Swagger file 和 future login/register API
                        .requestMatchers("/swagger-ui/**", "/v3/api-docs/**", "/api/auth/**").permitAll()
                        // All API (/api/customers)
                        .anyRequest().authenticated()
                );

        return http.build();
    }
}
