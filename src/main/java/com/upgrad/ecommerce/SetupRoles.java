package com.upgrad.ecommerce;

import com.upgrad.ecommerce.models.ERole;
import com.upgrad.ecommerce.services.RoleService;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SetupRoles implements ApplicationListener<ApplicationReadyEvent> {
    private final List<ERole> roles = List.of(ERole.ADMIN, ERole.USER);

    private final RoleService roleService;

    public SetupRoles(RoleService roleService) {
        this.roleService = roleService;
    }

    @Override
    public void onApplicationEvent(ApplicationReadyEvent applicationReadyEvent) {
        for (ERole role : roles) {
            try {
                roleService.create(role);
                System.out.println("Role Created " + role.name());
            } catch (Exception e) {
                System.out.println("Skipping Role Creation");
            }
        }
    }
}