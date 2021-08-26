package com.springmicro.foodometer.service;

import com.springmicro.foodometer.repository.AddressRepository;
import com.springmicro.foodometer.repository.CustomerRepository;
import com.springmicro.foodometer.web.mapper.AddressMapper;
import com.springmicro.foodometer.web.mapper.CustomerMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final AddressRepository addressRepository;
    private final CustomerMapper customerMapper;
    private final AddressMapper addressMapper;
}
