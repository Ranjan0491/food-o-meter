package com.springmicro.foodometer.repository;

import com.springmicro.foodometer.constants.FoodOrderStatus;
import com.springmicro.foodometer.document.FoodOrderDelivery;
import com.springmicro.foodometer.document.FoodOrderPreparation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FoodOrderPreparationRepository extends MongoRepository<FoodOrderPreparation, String> {

    FoodOrderPreparation findByFoodOrderId(String foodOrderId);
    List<FoodOrderPreparation> findByFoodOrderStatus(FoodOrderStatus foodOrderStatus);
    List<FoodOrderDelivery> findByStaffId(String staffId);
}
