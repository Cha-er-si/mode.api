package com.chaersi.mode_api.exception;

import com.chaersi.mode_api.dto.response.BaseResponse;
import com.chaersi.mode_api.entity.ErrorInfo;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(CustomException.class)
    public ResponseEntity<?> handleCustom(CustomException exception) {
        return ResponseEntity.badRequest().body(Map.of(
                "error", exception.getMessage()
        ));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public BaseResponse handleValidationErrors(MethodArgumentNotValidException exception) {
        List<ErrorInfo> errors = exception.getBindingResult()
                .getAllErrors()
                .stream()
                .map((error) -> new ErrorInfo(error.getDefaultMessage(), error.getCode().toUpperCase()))
                .collect(Collectors.toList());

        BaseResponse response = BaseResponse.builder()
                .status(HttpStatus.BAD_REQUEST.value())
                .error(errors)
                .build();

        return response;
    }
}
