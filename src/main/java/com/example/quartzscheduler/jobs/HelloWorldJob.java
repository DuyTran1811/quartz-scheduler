package com.example.quartzscheduler.jobs;

import com.example.quartzscheduler.api.model.Person;
import com.example.quartzscheduler.api.service.IPersonService;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobKey;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class HelloWorldJob implements Job {
    private static final Logger LOG = LoggerFactory.getLogger(HelloWorldJob.class);
    @Autowired
    protected IPersonService personService;
    private String idJob;

    @Override
    public void execute(JobExecutionContext context) {
        JobKey jobKey = context.getJobDetail().getKey();
        LOG.info("Remaining fire count is '{}'", jobKey + " id Job: " + idJob);
        String id = idJob;
        Person person = personService.finById(id);
        person.setStatus(false);
        personService.addPerson(person);
    }

    public void setIdJob(String idJob) {
        this.idJob = idJob;
    }
}
