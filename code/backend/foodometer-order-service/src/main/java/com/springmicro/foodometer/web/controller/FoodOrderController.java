package com.springmicro.foodometer.web.controller;

import com.springmicro.foodometer.document.FoodItem;
import com.springmicro.foodometer.service.FoodItemService;
import com.springmicro.foodometer.web.dto.FoodItemDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/food-o-meter/v1/services")
@RequiredArgsConstructor
public class FoodOrderController {

    private final FoodItemService foodItemService;

    @GetMapping(value = "/list-of-items", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<FoodItemDto>> getFoodItems(){
        List<FoodItemDto> foodItemDtoList = foodItemService.getAllFoodItems();
        return ResponseEntity.status(HttpStatus.OK).body(foodItemDtoList);
    }

}
