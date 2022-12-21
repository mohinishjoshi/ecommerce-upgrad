package com.upgrad.ecommerce.repos;

import com.upgrad.ecommerce.domain.Order;
import java.util.UUID;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface OrderRepository extends MongoRepository<Order, UUID> {
}
