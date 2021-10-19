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
    public FoodItemDto saveFoodItem(FoodItemDto foodItemDto) throws Exception {
        FoodItemDto foodItemDtoExists = getAllFoodItemsByName(foodItemDto.getItemName());
        if(foodItemDtoExists == null) {
            return foodItemMapper.foodItemToFoodItemDto(foodItemRepository.save(foodItemMapper.foodItemDtoToFoodItem(foodItemDto)));
        } else {
            throw new Exception("Food item " + foodItemDto.getItemName() + " already exists.");
        }
    }

    public FoodItemDto getFoodItemById(String id) {
        Optional<FoodItem> foodItemOptional = foodItemRepository.findById(id);
        if(foodItemOptional.isPresent()) {
            return foodItemMapper.foodItemToFoodItemDto(foodItemOptional.get());
        } else {
            return null;
        }
    }

    public FoodItemDto getAllFoodItemsByName(String name) {
        FoodItem foodItem = foodItemRepository.findAllByItemName(name);
        log.debug(foodItem.toString());
        return foodItemMapper.foodItemToFoodItemDto(foodItem);
    }

    @Transactional
    public FoodItemDto updateFoodItem(String id, FoodItemDto foodItemDto) throws Exception {
        FoodItemDto foodItemDtoExists = getFoodItemById(id);
        if(foodItemDtoExists != null) {
            return foodItemMapper.foodItemToFoodItemDto(foodItemRepository.save(foodItemMapper.foodItemDtoToFoodItem(foodItemDto)));
        } else {
            throw new Exception("Food item with id " + id + " does not exists.");
        }
    }

    public void deleteFoodItem(String id) {
        foodItemRepository.deleteById(id);
    }
}
