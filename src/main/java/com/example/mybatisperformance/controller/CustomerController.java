package com.example.mybatisperformance.controller;


import com.example.mybatisperformance.entity.Customer;
import com.example.mybatisperformance.service.CustomerService;
import com.example.mybatisperformance.type.CustomerType;
import com.example.mybatisperformance.type.PhoneType;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;

@RequestMapping("/customer")
@RestController
@RequiredArgsConstructor
public class CustomerController {
    private static Map<Integer, CustomerType> customerTypeById = Arrays.stream(CustomerType.values()).collect(Collectors.toMap(CustomerType::getId, e -> e));
    private static Map<Integer, PhoneType> phoneTypeById = Arrays.stream(PhoneType.values()).collect(Collectors.toMap(PhoneType::getId, e -> e));
    private final CustomerService service;
    private final Random random = new Random();

    @GetMapping(value = "/{id}")
    public Customer getCustomerById(@PathVariable Long id) {
        return service.getCustomerById(id);
    }

//    @GetMapping(value = "/name/{name}")
//    public Customer getCustomerByName(@PathVariable String name) {
//        return service.getCustomerByName(name);
//    }

    @GetMapping(value = "/type/{customerType}")
    public List<Customer> getCustomersByType(@PathVariable Integer customerType) {
        return service.getCustomersByType(customerTypeById.get(customerType));
    }

    @GetMapping(value = "/phoneType/{phoneType}")
    public List<Customer> getCustomerByPhoneType(@PathVariable Integer phoneType) {
        return service.getCustomerByPhoneType(phoneTypeById.get(phoneType));
    }

    @GetMapping(value = "/create")
    public Customer createCustomer() {
        return service.createCustomer(generateCustomer());
    }

    @GetMapping(value = "/updateBalance/{id}")
    public Customer updateCustomerBalance(@PathVariable Long id) {
        return service.updateCustomerBalance(id, BigDecimal.valueOf(random.nextDouble() * random.nextInt(10)));
    }

//    @DeleteMapping
//    public void deleteCustomer(Long customerId) {
//        service.deleteCustomer(customerId);
//    }

    private Customer generateCustomer() {
        Customer customer = new Customer();
        customer.setName("name" + random.nextInt(1001));
        customer.setCustomerType(customerTypeById.get(random.nextInt(6)));
        customer.setPhoneType(phoneTypeById.get(random.nextInt(4)));
        customer.setPhone("8-800-555-35-35");
        customer.setBalance(BigDecimal.valueOf(random.nextDouble() * random.nextInt(10)));
        customer.setIsActive(true);
        return customer;
    }
}
