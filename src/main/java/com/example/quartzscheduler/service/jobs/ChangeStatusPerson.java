//package com.example.quartzscheduler.service.jobs;
//
//import com.example.quartzscheduler.service.ICustomerService;
//import org.quartz.Job;
//import org.quartz.JobExecutionContext;
//import org.quartz.JobExecutionException;
//import org.springframework.stereotype.Component;
//
//@Component
//public class ChangeStatusPerson implements Job {
//
//    private ICustomerService personService;
//    private String idJob;
//
//
//    @Override
//    public void execute(JobExecutionContext context) throws JobExecutionException {
//        String id = idJob;
//        Person person = personService.finById(id);
//        person.setStatus(false);
//    }
//
//    public void setIdJob(String idJob) {
//        this.idJob = idJob;
//    }
//}
