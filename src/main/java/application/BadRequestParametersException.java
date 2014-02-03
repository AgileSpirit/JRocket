package application;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * User: OCTO-JBU
 * Date: 03/02/14
 * Time: 14:52
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Request parameters are missing or wrong.")
public class BadRequestParametersException extends RuntimeException {

    private final String message;

    public BadRequestParametersException(String message) {
        this.message = message;
    }

}
