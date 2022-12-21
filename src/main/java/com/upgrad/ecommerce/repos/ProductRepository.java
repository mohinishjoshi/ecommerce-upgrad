package com.upgrad.ecommerce.repos;

import com.upgrad.ecommerce.domain.Product;
import java.util.UUID;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface ProductRepository extends MongoRepository<Product, UUID> {
}
