package com.example.quartzscheduler.service;

import com.example.quartzscheduler.model.Customer;
import com.example.quartzscheduler.repository.CustomerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class CustomerServiceImpl implements ICustomerService {
    private static final Logger LOGGER = LoggerFactory.getLogger(CustomerServiceImpl.class);
    private final CustomerRepository repository;

    public CustomerServiceImpl(CustomerRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Customer> getAll() {
        return repository.findAll();
    }

    @Override
    public void createNew(Customer customer) {
        if (Objects.isNull(customer)) {
            customer = new Customer();
            LOGGER.error("Đối Tượng Rỗng !" + customer);
        }
        repository.save(customer);
    }

    @Override
    public void update(Customer customer) {
        if (Objects.isNull(customer)) {
            customer = new Customer();
            LOGGER.error("Đối Tượng Rỗng !" + customer);
        }
        repository.save(customer);
    }

    @Override
    public void delete(String id) {
        repository.deleteById(id);
    }

    @Override
    public Customer finById(String id) {
        if (id.isBlank()) {
            LOGGER.error("ID Null !" + id);
            return null;
        }
        return repository.findByID(id);
    }
}
