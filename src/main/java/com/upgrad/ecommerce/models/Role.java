package com.upgrad.ecommerce.models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@Document(collection = "roles")
public class Role {

    @Id
    private String id;

    @NotNull
    @Size(max = 10)
    @Indexed(unique = true)
    private String name;
}
