package com.chaersi.mode_api.controller;

import com.chaersi.mode_api.dto.request.DocumentRequestDTO;
import com.chaersi.mode_api.dto.response.DocumentRequestResponse;
import com.chaersi.mode_api.security.JWTService;
import com.chaersi.mode_api.service.DocumentRequestService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/document")
@RequiredArgsConstructor
public class DocumentRequestController {
    private final DocumentRequestService documentRequestService;
    private final JWTService jwtService;

    @PostMapping("/request")
    public ResponseEntity<DocumentRequestResponse> request(@RequestHeader("Authorization") String authHeader, @RequestBody DocumentRequestDTO documentRequestDTO) {
        String applicationId = jwtService.getApplicationId(authHeader);
        DocumentRequestResponse documentRequestResponse = documentRequestService.request(applicationId, documentRequestDTO);

        return ResponseEntity.ok(documentRequestResponse);
    }
}
