package com.upgrad.ecommerce.dto;

import com.upgrad.ecommerce.models.Role;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;

@Data
public class UserDTO {

    private String id;

    @NotNull
    @Size(max = 255)
    private String firstName;

    @NotNull
    @Size(max = 255)
    private String lastName;

    @NotNull
    @Size(max = 255)
    private String email;

    @NotNull
    @Size(max = 255)
    private String password;

    private Set<Role> roles;

    @NotNull
    @Size(max = 255)
    private String contactNumber;

}
