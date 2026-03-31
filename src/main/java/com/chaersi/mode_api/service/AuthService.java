package com.chaersi.mode_api.service;

import com.chaersi.mode_api.dto.request.AuthRequest;
import com.chaersi.mode_api.dto.response.AuthResponse;
import com.chaersi.mode_api.entity.ErrorInfo;
import org.springframework.stereotype.Component;

public interface AuthService {
    AuthResponse generateToken(AuthRequest request);
}
