package com.example.mybatisperformance.service;

import com.example.mybatisperformance.entity.Customer;
import com.example.mybatisperformance.mapper.CustomerMapper;
import com.example.mybatisperformance.type.CustomerType;
import com.example.mybatisperformance.type.PhoneType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerService {
    private final CustomerMapper mapper;

    public Customer getCustomerById(Long id) {
        return mapper.getById(id);
    }

//    public Customer getCustomerByName(String name) {
//        return mapper.findByName(name);
//    }

    public List<Customer> getCustomersByType(CustomerType customerType) {
        return mapper.findAllByCustomerType(customerType);
    }

    public List<Customer> getCustomerByPhoneType(PhoneType phoneType) {
        return mapper.findAllByPhoneType(phoneType);
    }

    public Customer createCustomer(Customer customer) {
        Long nextId = mapper.getNextId();
        customer.setId(nextId);
        customer.setVersion(0);
        customer.setUpdatedAt(OffsetDateTime.now());
        customer.setCreatedAt(OffsetDateTime.now());
        mapper.insert(customer);
        return customer;
    }

    @Transactional
    public Customer updateCustomerBalance(Long id, BigDecimal balance) {
        Customer customer = mapper.getById(id);
        customer.setBalance(balance);
        customer.setVersion(customer.getVersion() + 1);
        customer.setUpdatedAt(OffsetDateTime.now());
        mapper.update(customer);
        return customer;
    }
}
