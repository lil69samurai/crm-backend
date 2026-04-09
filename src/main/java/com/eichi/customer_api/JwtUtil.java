package com.eichi.customer_api;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtUtil {
    // 產生一把安全的加密金鑰 (每次重啟伺服器會換一把新的，適合目前開發測試用)
    private final Key secretKey = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    // 設定 JWT 的有效期限為 1 天 (86,400,000 毫秒)
    private final long expirationMs = 86400000;

    // ✅ 1. Create JWT Token
    public String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username) // 把帳號塞進 Token 裡面
                .setIssuedAt(new Date()) // 簽發時間
                .setExpiration(new Date(System.currentTimeMillis() + expirationMs)) // 到期時間
                .signWith(secretKey) // 用我們的金鑰加密簽名
                .compact();
    }
    // ✅ 2. 看懂鑰匙 (從 Token 拿出帳號名稱)
    public String extractUsername(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }
    // ✅ 3. 辨識真偽 (驗證 Token 是否合法/有沒有過期)
    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(secretKey).build().parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            // 如果過期、被竄改、格式錯誤，就會跑到這裡回傳 false
            return false;
        }
    }
}
