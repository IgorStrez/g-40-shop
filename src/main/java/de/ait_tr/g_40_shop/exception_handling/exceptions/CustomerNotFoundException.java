package de.ait_tr.g_40_shop.exception_handling.exceptions;

public class CustomerNotFoundException extends RuntimeException {

    public CustomerNotFoundException(String customerName) {
        super(String.format("Customer with name %s not found", customerName));
    }

    public CustomerNotFoundException(Long customerId) {
        super(String.format("Customer with id %d not found", customerId));
    }
}