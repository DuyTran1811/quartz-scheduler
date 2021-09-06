package com.example.quartzscheduler.api.service;

import com.example.quartzscheduler.api.model.Person;
import com.example.quartzscheduler.api.repository.PersonRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class PersonServiceImpl implements IPersonService {
    private static final Logger LOGGER = LoggerFactory.getLogger(PersonServiceImpl.class);
    private final PersonRepository repository;

    public PersonServiceImpl(PersonRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Person> getAll() {
        return repository.findAll();
    }

    @Override
    public void addPerson(Person person) {
        if (Objects.isNull(person)) {
            person = new Person();
            LOGGER.error("Lỗi Đối Tượng Rỗng !" + person);
        }
        repository.save(person);
    }

    @Override
    public void updatePerson(Person person) {
        if (Objects.isNull(person)) {
            person = new Person();
            LOGGER.error("Lỗi Đối Tượng Rỗng !" + person);
        }
        repository.save(person);
    }

    @Override
    public void deletePerson(String id) {
        repository.deleteById(id);
    }

    @Override
    public Person finById(String id) {
        if (id.isBlank()) {
            LOGGER.error("ID Null !" + id);
            return null;
        }
        return repository.findByPersonId(id);
    }
}
