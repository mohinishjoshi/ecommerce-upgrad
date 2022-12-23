package com.upgrad.ecommerce.models;

import lombok.Data;
import org.springframework.data.annotation.*;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.OffsetDateTime;
import java.util.Set;

@Data
@Document(collection = "products")
public class Product {

    @Id
    String id;
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

    @DocumentReference(lazy = true, lookup = "{ 'product' : ?#{#self._id} }")
    @ReadOnlyProperty
    private Set<Order> productOrders;

    @CreatedDate
    private OffsetDateTime dateCreated;

    @LastModifiedDate
    private OffsetDateTime lastUpdated;

    @Version
    private Integer version;
}
