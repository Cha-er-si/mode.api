package com.chaersi.mode_api.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AuthRequestDTO {
    @NotBlank(message = "deviceId is required.")
    private String deviceId;

    @NotBlank(message = "osVersion is required.")
    private String osVersion;

    @NotBlank(message = "dateToday is required.")
    private String dateToday;
}
