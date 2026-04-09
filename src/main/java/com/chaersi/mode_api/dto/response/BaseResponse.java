package com.chaersi.mode_api.dto.response;

import com.chaersi.mode_api.entity.ErrorInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class BaseResponse {
    private int status;
    private List<ErrorInfo> error;
}
