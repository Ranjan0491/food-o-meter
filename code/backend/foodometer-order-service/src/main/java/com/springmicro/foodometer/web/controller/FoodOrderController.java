package com.springmicro.foodometer.web.controller;

import com.springmicro.foodometer.constants.FoodOrderConstants;
import com.springmicro.foodometer.constants.FoodOrderStatus;
import com.springmicro.foodometer.service.FoodOrderService;
import com.springmicro.foodometer.web.dto.DetailedFoodOrderDto;
import com.springmicro.foodometer.web.dto.FoodOrderDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.List;

@RestController
@RequestMapping("/food-o-meter-order-service/v1")
@RequiredArgsConstructor
public class FoodOrderController {

    private final FoodOrderService foodOrderService;

    @GetMapping("/customers/{customerId}/orders")
    public ResponseEntity<List<FoodOrderDto>> getAllOrdersByCustomerId(@PathVariable String customerId,
                                                                       @RequestParam(defaultValue = FoodOrderConstants.SORT_ORDER_DESC) String sortOrder) {
        List<FoodOrderDto> foodOrderDtos = foodOrderService.getAllOrdersByCustomerId(customerId);
        if(FoodOrderConstants.SORT_ORDER_DESC.equalsIgnoreCase(sortOrder)) {
            foodOrderDtos.sort(Comparator.comparing(FoodOrderDto::getOrderTimestamp).reversed());
        } else {
            foodOrderDtos.sort(Comparator.comparing(FoodOrderDto::getOrderTimestamp));
        }
        return ResponseEntity.status(HttpStatus.OK)
                .body(foodOrderDtos);
    }

    @PostMapping("/customers/{customerId}/orders")
    public ResponseEntity<FoodOrderDto> saveOrder(@RequestBody FoodOrderDto foodOrderDto) {
        return ResponseEntity.ok(foodOrderService.saveOrder(foodOrderDto));
    }

    @GetMapping("/orders/{orderId}")
    public ResponseEntity<FoodOrderDto> getOrderByOrderId(@PathVariable String orderId) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(foodOrderService.getOrderByOrderId(orderId));
    }

    @GetMapping("/customers/{customerId}/orders/{orderId}")
    public ResponseEntity<DetailedFoodOrderDto> getOrderByCustomerIdAndOrderId(@PathVariable String customerId, @PathVariable String orderId) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(foodOrderService.getDetailedOrderByCustomerIdAndOrderId(customerId, orderId));
    }

    @PutMapping("/customers/{customerId}/orders/{orderId}/cancel")
    public ResponseEntity<FoodOrderDto> cancelOrderByCustomerIdAndOrderId(@PathVariable String customerId, @PathVariable String orderId) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(foodOrderService.cancelOrder(customerId, orderId));
    }

    @GetMapping("/orders/status/{orderStatus}")
    public ResponseEntity<List<FoodOrderDto>> getOrdersByOrderStatus(@PathVariable FoodOrderStatus foodOrderStatus) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(foodOrderService.getAllFoodOrdersByStatus(foodOrderStatus));
    }
}
