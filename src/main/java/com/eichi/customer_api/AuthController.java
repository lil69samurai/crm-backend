package com.eichi.customer_api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@Tag(name = "Auth API", description = "Operations for user authentication")
public class AuthController {

    private final JwtUtil jwtUtil;

    public AuthController(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @Operation(summary = "User Login", description = "Login to get JWT token. Use admin/123456 to test.")
    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@Valid @RequestBody LoginRequestDTO request) {

        // 模擬檢查帳號密碼 (實務上這裡會去資料庫撈 User 資料來比對)
        if ("admin".equals(request.getUsername()) && "123456".equals(request.getPassword())) {

            // 密碼正確！呼叫鎖匠幫我們打一把 JWT 鑰匙
            String token = jwtUtil.generateToken(request.getUsername());

            // 把 Token 包裝成 JSON 回傳給前端
            Map<String, String> response = new HashMap<>();
            response.put("token", token);
            return ResponseEntity.ok(response);

        } else {
            // 密碼錯誤，回傳 401 Unauthorized
            Map<String, String> response = new HashMap<>();
            response.put("error", "Invalid username or password");
            return ResponseEntity.status(401).body(response);
        }
    }
}