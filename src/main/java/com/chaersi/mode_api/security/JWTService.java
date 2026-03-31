package com.chaersi.mode_api.security;

import com.chaersi.mode_api.dto.request.AuthRequest;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import io.jsonwebtoken.Jwts;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class JWTService {
    private final SecretKey key = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    public String generateToken(AuthRequest request) {
        return Jwts.builder()
                .setSubject(request.getDeviceId())
                .claim("androidVersion", request.getAndroidVersion())
                .claim("dateToday", request.getDateToday())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 3600000))
                .signWith(key)
                .compact();
    }

    public SecretKey getKey() {
        return key;
    }
}
