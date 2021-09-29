package com.springmicro.foodometer.repository;

import com.springmicro.foodometer.document.FoodOrderDelivery;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FoodOrderDeliveryRepository extends MongoRepository<FoodOrderDelivery, String> {
}
