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
@RequestMapping("/food-o-meter-order-service/v1/food-items")
@RequiredArgsConstructor
public class FoodOrderController {

    private final FoodItemService foodItemService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<FoodItemDto>> getFoodItems(){
        List<FoodItemDto> foodItemDtoList = foodItemService.getAllFoodItems();
        return ResponseEntity.status(HttpStatus.OK).body(foodItemDtoList);
    }

    @GetMapping(value="/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<FoodItemDto> getFoodItemById(@PathVariable("id") String id){
        FoodItemDto foodItemDto = foodItemService.getFoodItemById(id);
        return ResponseEntity.status(HttpStatus.OK).body(foodItemDto);
    }

    @GetMapping(value="/by-name/{name}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<FoodItemDto>> getFoodItemsByName(@PathVariable("name") String name){
        List<FoodItemDto> foodItemDtoList = foodItemService.getAllFoodItemsByName(name);
        return ResponseEntity.status(HttpStatus.OK).body(foodItemDtoList);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<FoodItemDto> saveFoodItem(@RequestBody FoodItemDto foodItemDto) {
        if(foodItemDto!=null) {
            FoodItemDto foodItemDtoSaved = foodItemService.saveFoodItem(foodItemDto);
            return ResponseEntity.status(HttpStatus.CREATED).body(foodItemDtoSaved);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(foodItemDto);
        }
    }

}
