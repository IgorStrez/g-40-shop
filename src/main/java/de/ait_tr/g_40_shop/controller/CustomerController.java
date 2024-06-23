package de.ait_tr.g_40_shop.controller;

import de.ait_tr.g_40_shop.domain.dto.CustomerDto;
import de.ait_tr.g_40_shop.service.interfaces.CustomerService;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    private final CustomerService service;

    public CustomerController(CustomerService service) {
        this.service = service;
    }

    @PostMapping
    public CustomerDto save(@RequestBody CustomerDto customer) {
        return service.save(customer);
    }

    @GetMapping
    public List<CustomerDto> get(
            @RequestParam(required = false) Long id
    ) {
        if (id == null) {
            return service.getAllActiveCustomers();
        } else {
            CustomerDto customer = service.getActiveCustomerById(id);
            return customer == null ? null : List.of(customer);
        }
    }

    @PutMapping
    public CustomerDto update(@RequestBody CustomerDto customer) {
        return service.update(customer);
    }

    @DeleteMapping
    public void delete(
            @RequestParam(required = false) Long id,
            @RequestParam(required = false) String name
    ) {
        if (id != null) {
            service.deleteById(id);
        } else if (name != null) {
            service.deleteByName(name);
        }
    }

    @PutMapping("/restore")
    public void restore(@RequestParam Long id) {
        service.restoreById(id);
    }

    @GetMapping("/number")
    public long getCustomersNumber() {
        return service.getActiveCustomersNumber();
    }

    @GetMapping("/cart-cost")
    public BigDecimal getCartTotalCost(@RequestParam Long customerId) {
        return service.getCartTotalCost(customerId);
    }

    // GET -> http://localhost:8080/customers/avg-product-cost?customerId=5

    @GetMapping("/avg-product-cost")
    public BigDecimal getAverageProductCost(@RequestParam Long customerId) {
        return service.getAverageProductCost(customerId);
    }

    // PUT -> http://localhost:8080/customers/5/add-product/3

    @PutMapping("/{customerId}/add-product/{productId}")
    public void addProductToCustomersCart(
            @PathVariable Long customerId,
            @PathVariable Long productId
    ) {
        service.addProductToCustomersCart(customerId, productId);
    }

    // DELETE -> http://localhost:8080/customers/5/remove-product/3

    @DeleteMapping("/{customerId}/remove-product/{productId}")
    public void removeProductFromCustomersCart(
            @PathVariable Long customerId,
            @PathVariable Long productId
    ) {
        service.removeProductFromCustomersCart(customerId, productId);
    }

    // DELETE -> http://localhost:8080/customers/5/clear-cart

    @DeleteMapping("/{customerId}/clear-cart")
    public void clearCustomersCart(@PathVariable Long customerId) {
        service.clearCart(customerId);
    }
}