package com.chaersi.mode_api.security;

import com.chaersi.mode_api.dto.request.AuthRequestDTO;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.Jwts;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class JWTService {
    private final SecretKey key = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    public String generateToken(AuthRequestDTO request, String applicationId) {
        return Jwts.builder()
                .setSubject(request.getDeviceId())
                .claim("applicationId", applicationId)
                .claim("osVersion", request.getOsVersion())
                .claim("dateToday", request.getDateToday())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 3600000))
                .signWith(key)
                .compact();
    }

    public SecretKey getKey() {
        return key;
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token);

            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public String getApplicationId(String authHeader) {
        String token = authHeader.substring(7);
        String applicationId = Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .get("applicationId", String.class);

        return applicationId;
    }

    public String extractDeviceId(String token) {
        String deviceId = Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .get("deviceId", String.class);

        return deviceId;
    }
}
