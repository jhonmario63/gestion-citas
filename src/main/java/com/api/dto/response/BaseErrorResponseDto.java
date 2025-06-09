package com.api.dto.response;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;
import java.util.ArrayList;

@SuperBuilder
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonPropertyOrder({"status", "code", "message", "errors"})
public class BaseErrorResponseDto implements Serializable {
    private String status;
    private Integer code;
    private String message;
    private ArrayList<String> errors;
}