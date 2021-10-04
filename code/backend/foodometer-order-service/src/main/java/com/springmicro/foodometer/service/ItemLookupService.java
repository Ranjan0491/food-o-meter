package com.springmicro.foodometer.service;

import com.springmicro.foodometer.constants.FoodOrderConstants;
import com.springmicro.foodometer.web.dto.FoodItemDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
@Slf4j
public class ItemLookupService {
    private final RestTemplate restTemplate;

    public FoodItemDto fetchFoodItemDto(String id) {
        return restTemplate.getForObject("http://" + FoodOrderConstants.FOOD_ITEM_SERVICE_NAME + "/food-o-meter-item-service/v1/food-items/" + id, FoodItemDto.class);
    }
}
