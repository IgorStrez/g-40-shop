package de.ait_tr.g_40_shop.exception_handling.exceptions;

public class ProductNotFoundException extends RuntimeException {

    public ProductNotFoundException(String productTitle) {
        super(String.format("Product with title %s not found", productTitle));
    }
}