package com.upgrad.ecommerce.model;

import javax.validation.constraints.NotNull;
import java.util.UUID;


public class OrderDTO {

    private UUID id;

    @NotNull
    private Integer quantity;

    @NotNull
    private UUID user;

    @NotNull
    private UUID product;

    @NotNull
    private UUID address;

    public UUID getId() {
        return id;
    }

    public void setId(final UUID id) {
        this.id = id;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(final Integer quantity) {
        this.quantity = quantity;
    }

    public UUID getUser() {
        return user;
    }

    public void setUser(final UUID user) {
        this.user = user;
    }

    public UUID getProduct() {
        return product;
    }

    public void setProduct(final UUID product) {
        this.product = product;
    }

    public UUID getAddress() {
        return address;
    }

    public void setAddress(final UUID address) {
        this.address = address;
    }

}
