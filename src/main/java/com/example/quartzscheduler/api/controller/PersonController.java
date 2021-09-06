package com.example.quartzscheduler.api.controller;

import com.example.quartzscheduler.api.model.Person;
import com.example.quartzscheduler.api.service.IPersonService;
import com.example.quartzscheduler.service.ISchedulerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(path = "api")
public class PersonController {
    private final IPersonService personService;
    private final ISchedulerService schedulerService;

    public PersonController(IPersonService personService, ISchedulerService schedulerService) {
        this.personService = personService;
        this.schedulerService = schedulerService;
    }

    @RequestMapping(path = "/persons", method = RequestMethod.POST)
    public ResponseEntity<List<Person>> getPersons() {
        List<Person> personList = personService.getAll();
        return new ResponseEntity<>(personList, HttpStatus.OK);
    }

    @PostMapping(path = "/add")
    public ResponseEntity<Person> createPerson(@RequestBody Person person) {
        String id = UUID.randomUUID().toString();
        Person newPerson = Person.builder()
                .personId(id)
                .fullName(person.getFullName())
                .age(person.getAge())
                .address(person.getAddress())
                .status(person.isStatus())
                .build();
        personService.addPerson(newPerson);
        return new ResponseEntity<>(newPerson,HttpStatus.OK);
    }


}
