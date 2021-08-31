package com.springmicro.foodometer.repository;

import com.springmicro.foodometer.document.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {
}
