package com.api.controllers.errors_handler;

import com.api.dto.response.BaseErrorResponseDto;
import com.api.utils.exceptions.CustomException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.nio.file.AccessDeniedException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class GlobalExceptionHandler {

    private final String ERROR_MSG = "error";

    @ExceptionHandler(CustomException.class)
    @ResponseStatus()
    public ResponseEntity<BaseErrorResponseDto> customException(CustomException ex) {
        var errors = new ArrayList<String>();
        errors.add(ex.getMessage());
        var base = BaseErrorResponseDto.builder()
                .errors(errors)
                .status(ERROR_MSG)
                .code(ex.getStatus().value())
                .message(obtenerUltimaException(ex).getMessage())
                .build();
        return new ResponseEntity<>(base, ex.getStatus());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public BaseErrorResponseDto handleValidationExceptions(MethodArgumentNotValidException exception) {
        var errors = new ArrayList<String>();
        exception.getAllErrors().forEach(error -> errors.add(error.getDefaultMessage()));
        return BaseErrorResponseDto.builder()
                .errors(errors)
                .status(ERROR_MSG)
                .code(HttpStatus.BAD_REQUEST.value())
                .message("Validation failed")
                .build();
    }

    @ExceptionHandler(AccessDeniedException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public BaseErrorResponseDto handleAccessDeniedException(AccessDeniedException exception) {
        return BaseErrorResponseDto.builder()
                .status(ERROR_MSG)
                .code(HttpStatus.FORBIDDEN.value())
                .message("Access denied")
                .build();
    }


    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public BaseErrorResponseDto handleGeneralException(Exception exception) {
        return BaseErrorResponseDto.builder()
                .status(ERROR_MSG)
                .code(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .message("An unexpected error occurred")
                .build();
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public BaseErrorResponseDto handleNoHandlerFoundException(NoHandlerFoundException ex) {
        return BaseErrorResponseDto.builder()
                .status("error")
                .code(HttpStatus.NOT_FOUND.value())
                .message("The requested resource was not found")
                .errors(new ArrayList<>(List.of("Incorrect path or unavailable resource")))
                .build();
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public BaseErrorResponseDto dataIntegrityException(DataIntegrityViolationException ex) {
        return BaseErrorResponseDto.builder()
                .status("error")
                .code(HttpStatus.NOT_FOUND.value())
                .message("Integridad de datos")
                .errors(new ArrayList<>(List.of("Error de integridad de datos: " + ex.getMessage())))
                .build();
    }

    @ExceptionHandler(SQLException.class)
    public BaseErrorResponseDto handleSQLException(SQLException ex) {
        return BaseErrorResponseDto.builder()
                .status("error")
                .code(HttpStatus.NOT_FOUND.value())
                .message("Integridad de datos")
                .errors(new ArrayList<>(List.of("Error de integridad de datos: " + ex.getMessage())))
                .build();
    }

    private static Throwable obtenerUltimaException(Throwable e) {
        return e.getCause() != null ? obtenerUltimaException(e.getCause()) : e;
    }

}
