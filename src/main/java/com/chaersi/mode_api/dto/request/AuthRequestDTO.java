package com.chaersi.mode_api.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class AuthRequestDTO {
    @NotBlank(message = "deviceId is required.")
    private String deviceId;

    @NotBlank(message = "osVersion is required.")
    private String osVersion;

    @NotNull(message = "dateToday is required.")
    private Date dateToday;
}
