package de.ait_tr.g_40_shop.exception_handling.exceptions;

public class NoActiveProductsException extends RuntimeException {

    public NoActiveProductsException(String message) {
        super(message);
    }
}