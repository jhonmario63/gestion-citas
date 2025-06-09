package com.api.controllers;

import com.api.dto.response.ApiResponseDto;
import com.api.dto.response.BaseErrorResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

public class BaseController {

    protected ResponseEntity<ApiResponseDto> createSuccessResponse(Object data) {
        ApiResponseDto response = ApiResponseDto.builder()
                .data(data)
                .message("success")
                .status(HttpStatus.OK.value())
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    protected ResponseEntity<ApiResponseDto> createSuccessResponseList(List<Object> data) {
        ApiResponseDto response = ApiResponseDto.builder()
                .data(data)
                .message("success")
                .status(HttpStatus.OK.value())
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    protected ResponseEntity<ApiResponseDto> createCustomResponse(Object data, String message, HttpStatus status) {
        ApiResponseDto response = ApiResponseDto.builder()
                .data(data)
                .message(message)
                .status(status.value())
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    protected ResponseEntity<BaseErrorResponseDto> createErrorResponse(String message, HttpStatus status, ArrayList<String> errors) {
        BaseErrorResponseDto errorResponse = BaseErrorResponseDto.builder()
                .status(status.name())
                .code(status.value())
                .message(message)
                .errors(errors)
                .build();
        return ResponseEntity.status(status).body(errorResponse);
    }
}
