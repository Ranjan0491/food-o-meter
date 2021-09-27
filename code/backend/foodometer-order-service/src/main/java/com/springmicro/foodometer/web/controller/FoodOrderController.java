package com.springmicro.foodometer.web.controller;

import com.springmicro.foodometer.service.FoodOrderService;
import com.springmicro.foodometer.web.dto.FoodOrderDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/food-o-meter-order-service/v1")
@RequiredArgsConstructor
public class FoodOrderController {

    private final FoodOrderService foodOrderService;

    @GetMapping("/customers/{customerId}/orders")
    public ResponseEntity<List<FoodOrderDto>> getAllOrdersByCustomerId(@PathVariable String customerId) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(foodOrderService.getAllOrdersByCustomerId(customerId));
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
    public ResponseEntity<FoodOrderDto> getOrderByCustomerIdAndOrderId(@PathVariable String customerId, @PathVariable String orderId) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(foodOrderService.getOrderByCustomerIdAndOrderId(customerId, orderId));
    }

    @PutMapping("/customers/{customerId}/orders/{orderId}/cancel")
    public ResponseEntity<FoodOrderDto> cancelOrderByCustomerIdAndOrderId(@PathVariable String customerId, @PathVariable String orderId) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(foodOrderService.cancelOrder(customerId, orderId));
    }
}
