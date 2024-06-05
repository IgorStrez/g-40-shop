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
    public List<CustomerDto> get(@RequestParam(required = false) Long id) {
        if (id == null) {
            return service.getAllActiveCustomers();
        } else {
            CustomerDto customer = service.getById(id);
            return customer == null ? null : List.of(customer);
        }
    }

    @PutMapping
    public CustomerDto update(@RequestBody CustomerDto customer) {
        return service.update(customer);
    }

    @DeleteMapping
    public void delete(@RequestParam(required = false) Long id,
                       @RequestParam(required = false) String name) {
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

    @GetMapping("/quantity")
    public long getQuantity() {
        return service.getActiveCustomersQuantity();
    }

    @GetMapping("/total-price")
    public BigDecimal getCartTotalPrice(@RequestParam Long customerId) {
        return service.getCartTotalPrice(customerId);
    }

    @GetMapping("/average-price")
    public BigDecimal getCartAveragePrice(@RequestParam Long customerId) {
        return service.getCartAveragePrice(customerId);
    }

    @PostMapping("/add-product")
    public void addProductToCart(@RequestParam Long customerId, @RequestParam Long productId) {
        service.addProductToCart(customerId, productId);
    }

    @DeleteMapping("/remove-product")
    public void removeProductFromCart(@RequestParam Long customerId, @RequestParam Long productId) {
        service.removeProductFromCart(customerId, productId);
    }

    @DeleteMapping("/clear")
    public void clearCart(@RequestParam Long customerId) {
        service.clearCart(customerId);
    }
}
