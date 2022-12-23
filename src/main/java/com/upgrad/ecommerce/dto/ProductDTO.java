package com.upgrad.ecommerce.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Data
public class ProductDTO {

    private String id;

    @NotNull
    @Size(max = 255)
    private String name;

    @NotNull
    @Size(max = 255)
    private String category;

    @NotNull
    private Double price;

    private String description;

    @NotNull
    @Size(max = 255)
    private String manufacturer;

    @NotNull
    private Integer availableItems;

    @Size(max = 255)
    private String imageUrl;

}
