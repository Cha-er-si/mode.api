package com.chaersi.mode_api.dto.response;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@Builder
public class AuthResponse extends BaseResponse {
    private String token;
    private String applicationId;
}
