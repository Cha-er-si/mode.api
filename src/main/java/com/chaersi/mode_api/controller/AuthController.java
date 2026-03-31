package com.chaersi.mode_api.controller;

import com.chaersi.mode_api.dto.request.AuthRequest;
import com.chaersi.mode_api.dto.response.AuthResponse;
import com.chaersi.mode_api.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @GetMapping("/token-generate")
    public ResponseEntity<AuthResponse> generateToken(@RequestBody AuthRequest authRequest) {
        AuthResponse authResponse = authService.generateToken(authRequest);

        return ResponseEntity.ok(authResponse);
    }
}
