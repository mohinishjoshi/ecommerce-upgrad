package com.upgrad.ecommerce.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;


@Data
public class OrderDTO {

    private String id;

    @NotNull
    private Integer quantity;
    
    private String user;

    @NotNull
    private String product;

    @NotNull
    private String address;
}
