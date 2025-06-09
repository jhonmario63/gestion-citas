package com.api.dto.response;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Builder
@JsonPropertyOrder({"code", "message", "data"})
public class ApiResponseDto implements Serializable {
    private Object data;
    private String message;
    private Integer status;
}
