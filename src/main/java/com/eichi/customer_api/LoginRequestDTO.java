package com.eichi.customer_api;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

public class LoginRequestDTO {

    @Schema(description = "User account", example = "admin")
    @NotBlank(message = "Username cannot be empty")
    private String username;

    @Schema(description = "User password", example = "123456")
    @NotBlank(message = "Password cannot be empty")
    private String password;

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
}
