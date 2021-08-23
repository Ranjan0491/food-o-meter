package com.springmicro.foodometer.service;

import com.springmicro.foodometer.document.FoodItem;
import com.springmicro.foodometer.repository.FoodItemRepository;
import com.springmicro.foodometer.web.dto.FoodItemDto;
import com.springmicro.foodometer.web.mapper.FoodItemMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class FoodItemService {

    private final FoodItemRepository foodItemRepository;
    private final FoodItemMapper foodItemMapper;

    public List<FoodItemDto> getAllFoodItems() {
        List<FoodItem> foodItems = foodItemRepository.findAll();
        log.debug(foodItems.toString());
//        return foodItems;
        return foodItems
                .stream()
                .map(foodItem -> foodItemMapper.foodItemToFoodItemDto(foodItem))
                .collect(Collectors.toList());
    }
}
