package com.chaersi.mode_api.service;

import com.chaersi.mode_api.dto.request.AuthRequestDTO;
import com.chaersi.mode_api.dto.response.AuthResponse;

public interface AuthService {
    AuthResponse generateToken(AuthRequestDTO request);
}
