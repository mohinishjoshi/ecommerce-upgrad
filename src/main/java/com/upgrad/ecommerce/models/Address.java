package com.upgrad.ecommerce.models;

import lombok.Data;
import org.springframework.data.annotation.*;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.OffsetDateTime;
import java.util.Set;


@Data
@Document
public class Address {

    @Id
    private String id;

    @NotNull
    @Size(max = 255)
    private String name;

    @Indexed(unique = true)
    @NotNull
    @Size(max = 255)
    private String contactNumber;

    @NotNull
    @Size(max = 255)
    private String city;

    @NotNull
    @Size(max = 255)
    private String landmark;

    @NotNull
    @Size(max = 255)
    private String street;

    @NotNull
    @Size(max = 255)
    private String state;

    @NotNull
    @Size(max = 255)
    private String zipcode;

    @DocumentReference(lazy = true)
    @NotNull
    private User user;

    @DocumentReference(lazy = true, lookup = "{ 'address' : ?#{#self._id} }")
    @ReadOnlyProperty
    private Set<Order> addressOrders;

    @CreatedDate
    private OffsetDateTime dateCreated;

    @LastModifiedDate
    private OffsetDateTime lastUpdated;

    @Version
    private Integer version;

}
