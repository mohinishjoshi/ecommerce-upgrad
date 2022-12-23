package com.upgrad.ecommerce.repositories;

import com.upgrad.ecommerce.models.ERole;
import com.upgrad.ecommerce.models.Role;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends MongoRepository<Role, String> {
    Optional<Role> findByName(ERole name);
}
