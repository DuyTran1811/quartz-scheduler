package com.example.quartzscheduler.api.repository;

import com.example.quartzscheduler.api.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends JpaRepository<Person, String> {
    Person findByPersonId(String id);
}
