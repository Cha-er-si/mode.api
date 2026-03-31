package com.chaersi.mode_api.dto.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AuthRequest {
    private String deviceId;
    private String androidVersion;
    private String dateToday;
}
