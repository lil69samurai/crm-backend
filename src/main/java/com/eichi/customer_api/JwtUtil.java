package com.eichi.customer_api;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtUtil {
    // Read the fixed key from application.properties
    @Value("${jwt.secret}")
    private String secretString;

    // Generate a secure cryptographic key
    private final Key secretKey = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    // Set the validity period of JWT to 1 day. (86,400,000 毫秒)
    private final long expirationMs = 86400000;

    //1. Create JWT Token
    public String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username) // 把帳號塞進 Token 裡面
                .setIssuedAt(new Date()) // 簽發時間
                .setExpiration(new Date(System.currentTimeMillis() + expirationMs)) // 到期時間
                .signWith(secretKey) // 用我們的金鑰加密簽名
                .compact();
    }
    //2. Extracting the account name from the token
    public String extractUsername(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }
    //3. Verify authenticity (check if the token is valid/has expired).
    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(secretKey).build().parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            // If the file is expired, altered, or has an incorrect format, it will be returned here as a false error.
            return false;
        }
    }
}
