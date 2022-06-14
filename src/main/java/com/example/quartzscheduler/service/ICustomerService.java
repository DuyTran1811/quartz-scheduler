package com.example.quartzscheduler.service;

import com.example.quartzscheduler.model.Customer;

import java.util.List;

public interface ICustomerService {
    List<Customer> getAll();

    void createNew(Customer customer);

    void update(Customer customer);

    void delete(String customerId);

    Customer finById(String customerId);
}
