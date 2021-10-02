package com.springmicro.foodometer.repository;

import com.springmicro.foodometer.constants.FoodOrderStatus;
import com.springmicro.foodometer.document.FoodOrder;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FoodOrderRepository extends MongoRepository<FoodOrder, String> {
    @Query("{'customerId': {$regex: ?0 }})")
    List<FoodOrder> findAllByCustomerId(String customerId);

    List<FoodOrder> findByOrderStatus(FoodOrderStatus foodOrderStatus);

    List<FoodOrder> findByOrderStatusIn(List<FoodOrderStatus> foodOrderStatuses);
}
