package com.springmicro.foodometer.repository;

import com.springmicro.foodometer.constants.FoodOrderStatus;
import com.springmicro.foodometer.document.FoodOrderDelivery;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FoodOrderDeliveryRepository extends MongoRepository<FoodOrderDelivery, String> {
    List<FoodOrderDelivery> findByFoodOrderStatus(FoodOrderStatus foodOrderStatus);
}
