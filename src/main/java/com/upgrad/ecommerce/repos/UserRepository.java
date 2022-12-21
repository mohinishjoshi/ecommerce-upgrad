package com.upgrad.ecommerce.repos;

import com.upgrad.ecommerce.domain.User;
import java.util.UUID;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface UserRepository extends MongoRepository<User, UUID> {
}
