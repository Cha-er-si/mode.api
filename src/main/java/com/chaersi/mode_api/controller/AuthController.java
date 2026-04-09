package com.chaersi.mode_api.controller;

import com.chaersi.mode_api.dto.request.AuthRequestDTO;
import com.chaersi.mode_api.dto.response.AuthResponse;
import com.chaersi.mode_api.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/token-generate")
    public ResponseEntity<AuthResponse> generateToken(@Valid @RequestBody AuthRequestDTO authRequestDTO) {
        AuthResponse authResponse = authService.generateToken(authRequestDTO);

        return ResponseEntity.ok(authResponse);
    }
}
