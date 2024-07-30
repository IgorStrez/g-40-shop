package de.ait_tr.g_40_shop.exception_handling.exceptions;

public class ProductNotFoundException extends RuntimeException {

    public ProductNotFoundException(Long productId) {
        super(String.format("Product with id %d not found", productId));
    }
}