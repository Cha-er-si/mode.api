package com.chaersi.mode_api.service.impl;

import ch.qos.logback.core.util.StringUtil;
import com.chaersi.mode_api.dto.request.AuthRequest;
import com.chaersi.mode_api.dto.response.AuthResponse;
import com.chaersi.mode_api.entity.ApplicationRequest;
import com.chaersi.mode_api.exception.CustomException;
import com.chaersi.mode_api.repository.ApplicationRequestRepository;
import com.chaersi.mode_api.security.JWTService;
import com.chaersi.mode_api.service.AuthService;
import com.chaersi.mode_api.util.ApplicationIDUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final JWTService jwtService;
    private final ApplicationIDUtil applicationIDUtil;
    private final ApplicationRequestRepository applicationRequestRepository;
    private final ObjectMapper objectMapper;

    @Override
    public AuthResponse generateToken(AuthRequest authRequest) {
        validateRequest(authRequest);

        String token = jwtService.generateToken(authRequest);

        String applicationId = applicationIDUtil.generateApplicationID();

        AuthResponse authResponse = AuthResponse
                .builder()
                .status(200)
                .token(token)
                .applicationId(applicationId)
                .build();

        try{
            applicationRequestRepository.save(
                    ApplicationRequest
                            .builder()
                            .applicationId(applicationId)
                            .requestUrl("/api/v1/auth/token-generate")
                            .responseBody(objectMapper.writeValueAsString(authResponse))
                            .requestBody(objectMapper.writeValueAsString(authRequest))
                            .build()
            );
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return authResponse;
    }

    private void validateRequest(AuthRequest authRequest) {
        if(StringUtil.isNullOrEmpty(authRequest.getDeviceId())) {
            throw new CustomException("Device ID Required");
        }
    }
}
