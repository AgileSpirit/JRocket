package application;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created with IntelliJ IDEA.
 * User: OCTO-JBU
 * Date: 01/02/14
 * Time: 00:51
 * To change this template use File | Settings | File Templates.
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "No such bookmark")
public class BookmarkNotFoundException extends RuntimeException {
}
