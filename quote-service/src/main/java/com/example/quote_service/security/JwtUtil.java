package com.example.quote_service.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import java.util.Date;

@Component
public class JwtUtil {

    @Value("${jwt.secret}")
    private String jwtSecret;

    public Claims parseToken(String token) {
        System.out.println("JWT Secret: " + jwtSecret);
        System.out.println("Token to validate: " + token);

        try {
            Claims claims = Jwts.parser()
                    .setSigningKey(jwtSecret)
                    .parseClaimsJws(token)
                    .getBody();
            System.out.println("Token parsed successfully");
            return claims;
        } catch (Exception e) {
            System.out.println("Token parse error: " + e.getMessage());
            throw e;
        }
    }

    public boolean validateToken(String token) {
        try {
            Claims claims = parseToken(token);
            boolean expired = claims.getExpiration().after(new Date());
            System.out.println("Token expired: " + !expired);
            return expired;
        } catch (Exception e) {
            System.out.println("Validation error: " + e.getMessage());
            return false;
        }
    }

    public String getUsernameFromToken(String token) {
        return parseToken(token).getSubject();
    }

    public Integer getUserIdFromToken(String token) {
        return parseToken(token).get("userId", Integer.class);
    }
}