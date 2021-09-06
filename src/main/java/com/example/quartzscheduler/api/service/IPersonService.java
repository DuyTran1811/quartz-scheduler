package com.example.quartzscheduler.api.service;

import com.example.quartzscheduler.api.model.Person;

import java.util.List;

public interface IPersonService {
    List<Person> getAll();

    void addPerson(Person person);

    void updatePerson(Person person);

    void deletePerson(String id);

    Person finById(String id);
}
