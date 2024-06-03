package de.ait_tr.g_40_shop.service.interfaces;

import de.ait_tr.g_40_shop.domain.entity.Customer;

import java.math.BigDecimal;
import java.util.List;

public interface CustomerService {
    Customer save(Customer customer);
    List<Customer> getAllActiveCustomers();
    Customer getById(Long id);
    Customer update(Customer customer);
    void deleteById(Long id);
    void deleteByName(String name);
    void restoreById(Long id);
    long getActiveCustomersQuantity();
    BigDecimal getCartTotalPrice(Long customerId);
    BigDecimal getCartAveragePrice(Long customerId);
    void addProductToCart(Long customerId, Long productId);
    void removeProductFromCart(Long customerId, Long productId);
    void clearCart(Long customerId);
}
