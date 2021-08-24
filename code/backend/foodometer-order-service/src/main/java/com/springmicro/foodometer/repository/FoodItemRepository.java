package com.springmicro.foodometer.repository;

import com.springmicro.foodometer.document.FoodItem;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FoodItemRepository extends MongoRepository<FoodItem, String> {

    FoodItem findAllByItemName(String name);

//    @Query("{'itemName': {$regex: ?0 }})")
//    List<FoodItem> findAllByItemNameLike(String userName);
}
