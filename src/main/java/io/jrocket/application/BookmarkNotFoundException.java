package io.jrocket.application;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * User: OCTO-JBU
 * Date: 01/02/14
 * Time: 00:51
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "No such bookmark")
public class BookmarkNotFoundException extends RuntimeException {
}
