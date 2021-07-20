package com.springmicro.foodometer.controller;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/food-o-meter/v1/services")
public class FoodOrderController {

    @GetMapping("/listofitems")
    public List<String> getFoodItems(){
        List<String> foodList = new ArrayList<>();
        foodList.add("Biryani");
        foodList.add("Noodles");

        return foodList;
    }

}
