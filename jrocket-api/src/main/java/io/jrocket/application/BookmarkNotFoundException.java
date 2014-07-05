package io.jrocket.application;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 *
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "No such bookmark")
public class BookmarkNotFoundException extends RuntimeException {
}
