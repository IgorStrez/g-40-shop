package de.ait_tr.g_40_shop.exception_handling.exceptions;

public class CustomerInactiveException extends RuntimeException {

    public CustomerInactiveException(String customerName) {
        super(String.format("Customer with name %s is inactive", customerName));
    }

    public CustomerInactiveException(Long customerId) {
        super(String.format("Customer with id %d is inactive", customerId));
    }
}