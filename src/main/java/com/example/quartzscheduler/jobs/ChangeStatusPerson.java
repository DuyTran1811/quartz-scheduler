package com.example.quartzscheduler.jobs;

import com.example.quartzscheduler.api.model.Person;
import com.example.quartzscheduler.api.service.IPersonService;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.stereotype.Component;

@Component
public class ChangeStatusPerson implements Job {

    private IPersonService personService;
    private String idJob;


    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        String id = idJob;
        Person person = personService.finById(id);
        person.setStatus(false);
    }

    public void setIdJob(String idJob) {
        this.idJob = idJob;
    }
}
