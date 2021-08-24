package com.springmicro.foodometer.service;

import com.springmicro.foodometer.document.FoodItem;
import com.springmicro.foodometer.repository.FoodItemRepository;
import com.springmicro.foodometer.web.dto.FoodItemDto;
import com.springmicro.foodometer.web.mapper.FoodItemMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class FoodItemService {

    private final FoodItemRepository foodItemRepository;
    private final FoodItemMapper foodItemMapper;

    public List<FoodItemDto> getAllFoodItems() {
        List<FoodItem> foodItems = foodItemRepository.findAll();
        log.info("Fetched items: " + foodItems.toString());
        List<FoodItemDto> foodItemDtos =  foodItems
                .stream()
                .map(foodItem -> foodItemMapper.foodItemToFoodItemDto(foodItem))
                .collect(Collectors.toList());
        log.info("Fetched items DTO: " + foodItemDtos.toString());
        return foodItemDtos;
    }

    @Transactional
    public FoodItemDto saveFoodItem(FoodItemDto foodItemDto) {
        return foodItemMapper.foodItemToFoodItemDto(foodItemRepository.save(foodItemMapper.foodItemDtoToFoodItem(foodItemDto)));
    }

    public FoodItemDto getFoodItemById(String id) {
        Optional<FoodItem> foodItemOptional = foodItemRepository.findById(id);
        if(foodItemOptional.isPresent()) {
            return foodItemMapper.foodItemToFoodItemDto(foodItemOptional.get());
        } else {
            return null;
        }
    }

    public List<FoodItemDto> getAllFoodItemsByName(String name) {
        List<FoodItem> foodItems = foodItemRepository.findAllByItemName(name);
        log.debug(foodItems.toString());
        return foodItems
                .stream()
                .map(foodItem -> foodItemMapper.foodItemToFoodItemDto(foodItem))
                .collect(Collectors.toList());
    }
}
