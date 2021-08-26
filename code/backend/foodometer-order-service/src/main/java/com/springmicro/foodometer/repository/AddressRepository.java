package com.springmicro.foodometer.repository;

import com.springmicro.foodometer.document.Address;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AddressRepository extends MongoRepository<Address, String> {
}
