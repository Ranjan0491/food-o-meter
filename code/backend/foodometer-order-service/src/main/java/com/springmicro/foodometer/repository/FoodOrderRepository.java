package com.springmicro.foodometer.repository;

import com.springmicro.foodometer.document.FoodOrder;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FoodOrderRepository extends MongoRepository<FoodOrder, String> {
    @Query("{'customerId': {$regex: ?0 }})")
    List<FoodOrder> findAllByCustomerId(String customerId);

    @Query("{'customerId': {$regex: ?0 }, 'id': {$regex: ?1 }})")
    FoodOrder findOrderByCustomerIdAndOrderId(String customerId, String orderId);
}
