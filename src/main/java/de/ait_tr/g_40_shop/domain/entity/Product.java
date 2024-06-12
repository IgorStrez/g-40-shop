package de.ait_tr.g_40_shop.domain.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    // Название должно быть длиной хотя бы 3 символа
    // Название должно начинаться с заглавной буквы
    // Остальные буквы в названии должны быть строчными Латинскими (разрешаются пробелы)
    // Без цифр и символов
    // Название не должно быть null

    @NotNull(message = "Product title cannot be null")
    @NotBlank(message = "Product title cannot be empty")
    @Pattern(
            regexp = "[A-Z][a-z ]{2,31}",
            message = "Product title should be at least 3 character and start with capital letter"
    )
    @Column(name = "title")
    private String title;

    @DecimalMin(
            value = "5.00",
            message = "Product price should be greater or equal than 5.00"
    )
    @DecimalMax(
            value = "100000.00",
            inclusive = false,
            message = "Product price should be lesser than 100000.00"
    )
    @Column(name = "price")
    private BigDecimal price;

    @Column(name = "active")
    private boolean active;

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public boolean isActive() {
        return active;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return active == product.active && Objects.equals(id, product.id) && Objects.equals(title, product.title) && Objects.equals(price, product.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, price, active);
    }

    @Override
    public String toString() {
        return String.format("Product: id - %d, title - %s, price - %s, active - %s",
                id, title, price, active ? "yes" : "no");
    }
}