package com.springmicro.foodometer.repository;

import com.springmicro.foodometer.document.FoodOrderPreparation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FoodOrderPreparationRepository extends MongoRepository<FoodOrderPreparation, String> {

    FoodOrderPreparation findByFoodOrderId(String foodOrderId);
}
