package com.springmicro.foodometer.service;

import com.springmicro.foodometer.document.Address;
import com.springmicro.foodometer.document.Customer;
import com.springmicro.foodometer.exception.CustomerException;
import com.springmicro.foodometer.repository.AddressRepository;
import com.springmicro.foodometer.repository.CustomerRepository;
import com.springmicro.foodometer.web.dto.AddressDto;
import com.springmicro.foodometer.web.dto.CustomerDto;
import com.springmicro.foodometer.web.mapper.AddressMapper;
import com.springmicro.foodometer.web.mapper.CustomerMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final AddressRepository addressRepository;
    private final CustomerMapper customerMapper;
    private final AddressMapper addressMapper;

    @Transactional
    public CustomerDto saveCustomer(CustomerDto customerDto) {
        if(customerDto!=null) {
            if(customerDto.getAddresses()!=null && customerDto.getAddresses().size()>0) {
                List<AddressDto> savedAddressList = new ArrayList<>();
                customerDto.getAddresses().forEach(addressDto -> savedAddressList.add(addressMapper.addressToAddressDto(addressRepository.save(addressMapper.addressDtoToAddress(addressDto)))));
                log.debug("Addresses saved for customer : "+customerDto);

                CustomerDto savedCustomerDto = customerMapper.customerToCustomerDto(customerRepository.save(customerMapper.customerDtoToCustomer(customerDto)));
                savedCustomerDto.setAddresses(savedAddressList);
                log.debug("Customer saved : "+savedCustomerDto);
                return  savedCustomerDto;
            } else {
                throw new CustomerException("No address is present for customer.");
            }
        } else {
            throw new CustomerException("Customer object is null.");
        }
    }

    public CustomerDto getCustomerById(String id) {
        if(StringUtils.isNotBlank(id)) {
            Optional<Customer> optionalCustomer = customerRepository.findById(id);
            if(optionalCustomer.isPresent())
                return customerMapper.customerToCustomerDto(optionalCustomer.get());
            else
                throw new CustomerException("No customer found with id - "+id);
        } else {
            throw new CustomerException("Customer Id is missing");
        }
    }
}
