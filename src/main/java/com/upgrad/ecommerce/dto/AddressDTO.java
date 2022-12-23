package com.upgrad.ecommerce.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Data
public class AddressDTO {

    private String id;

    @NotNull
    @Size(max = 255)
    private String name;

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

    @NotNull
    private String user;
}
