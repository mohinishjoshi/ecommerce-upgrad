package com.upgrad.ecommerce.repositories;

import com.upgrad.ecommerce.models.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductRepository extends MongoRepository<Product, String> {

    @Override
    void delete(Product deleted);
}
