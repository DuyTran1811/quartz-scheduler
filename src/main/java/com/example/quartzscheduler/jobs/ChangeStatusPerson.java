package com.example.quartzscheduler.jobs;

import com.example.quartzscheduler.api.service.IPersonService;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobKey;
import org.springframework.stereotype.Component;

@Component
public class ChangeStatusPerson implements Job {
    private final IPersonService personService;

    public ChangeStatusPerson(IPersonService personService) {
        this.personService = personService;
    }

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        /* Get message id recorded by scheduler during scheduling */
//        JobDataMap dataMap = context.getJobDetail().getJobDataMap();
        JobKey id = context.getJobDetail().getKey();
        System.out.println(id);
    }
}
