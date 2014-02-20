package io.jrocket.application;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 *
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Request parameters are missing or wrong.")
public class BadRequestParametersException extends RuntimeException {

    private final String message;

    public BadRequestParametersException(String message) {
        this.message = message;
    }

}
