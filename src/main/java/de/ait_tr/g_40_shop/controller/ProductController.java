package de.ait_tr.g_40_shop.controller;

import de.ait_tr.g_40_shop.domain.entity.Product;
import de.ait_tr.g_40_shop.service.interfaces.ProductService;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
        private ProductService service;
        public ProductController(ProductService service) {
        this.service = service;
    }

    //   CRUD - Create (POST), Read (GET), Update (PUT), Delete (DELETE)

    // Create product - POST - localhost:8080/products

    @PostMapping
    public Product save(@RequestBody Product product) {
        //TODO обращение к сервису
        return service.save(product);
    }

    // Get product - GET - localhost:8080/products

    @GetMapping
    public List<Product> get(@RequestParam(required = false) Long id) {
        if (id== null) {
            return service.getAllActiveProducts();
        } else {
           Product product = service.getById(id);
           return product == null ? null : List.of(product);
        }
    }

    // Update product - PUT - localhost:8080/products

    @PutMapping
    public Product update(@RequestBody Product product) {
       return service.update(product);
    }

    // Delete product - DELETE - localhost:8080/products?id=3

    @DeleteMapping
    public void delete(@RequestParam (required = false) Long id,
                       @RequestParam(required = false) String title) {
            if (id != null) {
                service.deleteById(id);
            } else if (title != null) {
                service.deleteByTitle(title);
            }
    }

    // Restore product - PUT - localhost:8080/products/restore


    @PutMapping("/restore")
    public void restore(@RequestParam Long id) {
            service.restoreById(id);
    }

    @GetMapping("/quantity")
    public long getQuantity() {
            return service.getActiveProductsQuantity();
    }

    @GetMapping("/total-price")
    public BigDecimal getTotalPrice() {
            return  service.getActiveProductsTotalPrice();
    }

    @GetMapping("/average-price")
    public BigDecimal getAveragePrice() {
        return  service.getActiveProductsAveragePrice();
    }

}















