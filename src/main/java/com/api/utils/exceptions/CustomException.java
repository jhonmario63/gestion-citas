package com.api.utils.exceptions;

import com.api.utils.MensajesEnum;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Setter
@Getter
public class CustomException extends RuntimeException {
    private HttpStatus status;

    public CustomException(String message) {
        super(message);
    }

    public CustomException(MensajesEnum message, HttpStatus status) {
        super(String.valueOf(message));
        this.status = status;
    }

    public CustomException(Throwable cause) {
        super(cause);
    }
}
