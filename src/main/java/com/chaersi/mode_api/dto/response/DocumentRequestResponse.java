package com.chaersi.mode_api.dto.response;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

@Data
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
public class DocumentRequestResponse extends BaseResponse {
    private String receptionNumber;
}
