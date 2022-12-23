package com.upgrad.ecommerce.models;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import javax.validation.constraints.NotNull;
import java.time.OffsetDateTime;


@Data
@Document
public class Order {

    @Id
    private String id;

    @NotNull
    private Integer quantity;

    @DocumentReference(lazy = true)
    @NotNull
    private User user;

    @DocumentReference(lazy = true)
    @NotNull
    private Product product;

    @DocumentReference(lazy = true)
    @NotNull
    private Address address;

    @CreatedDate
    private OffsetDateTime dateCreated;

    @LastModifiedDate
    private OffsetDateTime lastUpdated;

    @Version
    private Integer version;

}
