package com.springmicro.foodometer.web.controller;

import com.springmicro.foodometer.service.CustomerService;
import com.springmicro.foodometer.web.dto.CustomerDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/food-o-meter-order-service/v1/customers")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping
    public ResponseEntity<Void> saveCustomer(@RequestBody CustomerDto customerDto) {
        CustomerDto savedCustomerDto = customerService.saveCustomer(customerDto);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerDto> getCustomerById(@PathVariable("id") String id) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(customerService.getCustomerById(id));
    }
}
