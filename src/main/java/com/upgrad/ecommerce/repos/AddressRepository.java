package com.upgrad.ecommerce.repos;

import com.upgrad.ecommerce.domain.Address;
import java.util.UUID;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface AddressRepository extends MongoRepository<Address, UUID> {

    boolean existsByContactNumberIgnoreCase(String contactNumber);

}
