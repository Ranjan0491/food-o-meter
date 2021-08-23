package com.springmicro.foodometer.repository;

import com.springmicro.foodometer.document.FoodItem;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FoodItemRepository extends MongoRepository<FoodItem, String> {
}
