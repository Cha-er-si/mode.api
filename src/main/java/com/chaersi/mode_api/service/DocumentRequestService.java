package com.chaersi.mode_api.service;

import com.chaersi.mode_api.dto.request.DocumentRequestDTO;
import com.chaersi.mode_api.dto.response.DocumentRequestResponse;

public interface DocumentRequestService {
    DocumentRequestResponse request(String applicationId, DocumentRequestDTO documentRequestDTO);
}
