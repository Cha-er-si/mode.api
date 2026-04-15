package com.chaersi.mode_api.service.impl;

import ch.qos.logback.core.util.StringUtil;
import com.chaersi.mode_api.dto.request.AuthRequestDTO;
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
    public AuthResponse generateToken(AuthRequestDTO authRequestDTO) {
        String applicationId = applicationIDUtil.generateApplicationID(authRequestDTO.getBusinessCode());

        String token = jwtService.generateToken(authRequestDTO, applicationId);

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
                            .requestBody(objectMapper.writeValueAsString(authRequestDTO))
                            .build()
            );
        } catch (Exception exception) {
            throw new RuntimeException(exception);
        }

        return authResponse;
    }
}
