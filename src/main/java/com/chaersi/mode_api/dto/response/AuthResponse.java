package com.chaersi.mode_api.dto.response;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

@Data
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
public class AuthResponse extends BaseResponse {
    private String token;
    private String applicationId;
}
