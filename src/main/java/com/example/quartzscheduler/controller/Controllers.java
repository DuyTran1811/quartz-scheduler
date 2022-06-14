package com.example.quartzscheduler.controller;

import com.example.quartzscheduler.info.TimerInfo;
import com.example.quartzscheduler.model.HelloWorld;
import com.example.quartzscheduler.service.ISchedulerService;
import com.example.quartzscheduler.util.MisfirePolicy;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping(path = "/api")
public class Controllers {
    //    private final IPersonService personService;
    private final ISchedulerService schedulerService;

    //
    public Controllers(ISchedulerService schedulerService) {
//        this.personService = personService;
        this.schedulerService = schedulerService;
    }

    @GetMapping("/testjob")
    public String testJob() {
        HelloWorld helloWorld = new HelloWorld();
        helloWorld.setId("11111");
        TimerInfo timerInfo = new TimerInfo("TestJob",2,20,false,false,"Test Run",false, MisfirePolicy.FIRE_NOW);
        schedulerService.createdSchedule(HelloWorld.class, timerInfo);
        return "OK";
    }
//
//    @RequestMapping(path = "/persons", method = RequestMethod.POST)
//    public ResponseEntity<List<Person>> getPersons() {
//        List<Person> personList = personService.getAll();
//        return new ResponseEntity<>(personList, HttpStatus.OK);
//    }
//
//    @PostMapping(path = "/add")
//    public ResponseEntity<Person> createPerson(@RequestBody Person person) {
//        String id = UUID.randomUUID().toString();
//        Person newPerson = Person.builder()
//                .personId(id)
//                .fullName(person.getFullName())
//                .age(person.getAge())
//                .address(person.getAddress())
//                .status(person.isStatus())
//                .build();
//        personService.addPerson(newPerson);
//        return new ResponseEntity<>(newPerson, HttpStatus.OK);
//    }
//
//    @PostMapping(path = "/test/{id}")
//    public ResponseEntity<Person> testJob(@PathVariable String id) {
//        Person edit = personService.finById(id);
//        boolean result = schedulerService.schedule(HelloWorldJob.class, edit.getPersonId(), 10);
//        if (result) {
//            return new ResponseEntity<>(HttpStatus.OK);
//        } else {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//
//    }
//
//    @DeleteMapping(path = "/delete/{jobName}")
//    public String deleteJob(@PathVariable String jobName) {
//       boolean result = schedulerService.deleteJob(jobName);
//        return "OK";
//    }
}
