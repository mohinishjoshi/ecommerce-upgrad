package com.upgrad.ecommerce.services;

import com.upgrad.ecommerce.models.ERole;
import com.upgrad.ecommerce.models.Role;
import com.upgrad.ecommerce.repositories.ProductRepository;
import com.upgrad.ecommerce.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RoleService {
    private final RoleRepository roleRepository;

    public RoleService(final RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public void create(final ERole roleName) {
        Role role = new Role();
        role.setName(roleName.name());
        roleRepository.save(role);
    }
}
