package de.ait_tr.g_40_shop.domain.dto;

import de.ait_tr.g_40_shop.domain.entity.Cart;

import java.util.Objects;

public class CustomerDto {
    private Long id;
    private String name;
//    private Cart cart;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

//    public Cart getCart() {
//        return cart;
//    }
//
//    public void setCart(Cart cart) {
//        this.cart = cart;
//    }
//
//
//
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        CustomerDto that = (CustomerDto) o;
//        return Objects.equals(id, that.id) &&
//                Objects.equals(name, that.name) &&
//                Objects.equals(cart, that.cart);
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(id, name, cart);
//    }
//
//    @Override
//    public String toString() {
//        return String.format("Customer DTO: id - %d, name - %s, cart - %s",
//                id, name, cart == null ? "ERROR! Cart is missing" : cart);
//    }
}
