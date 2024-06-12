package de.ait_tr.g_40_shop.exception_handling.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class SecondTestException extends RuntimeException{

    public SecondTestException(String message) {
        super(message);
    }
}
