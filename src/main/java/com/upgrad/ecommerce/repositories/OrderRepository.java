package com.upgrad.ecommerce.repositories;

import com.upgrad.ecommerce.models.Order;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface OrderRepository extends MongoRepository<Order, String> {
}
