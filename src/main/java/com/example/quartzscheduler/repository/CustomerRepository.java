package com.example.quartzscheduler.repository;

import com.example.quartzscheduler.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, String> {
    @Query("SELECT c FROM Customer c WHERE c.customerId =: customerId")
    Customer findByID(String customerId);
}
