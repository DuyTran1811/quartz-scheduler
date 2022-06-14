package com.example.quartzscheduler.model;

import lombok.Data;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobKey;
@Data
public class HelloWorld implements Job {
    private String id;
    public HelloWorld() {
    }

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        JobKey jobKey = context.getJobDetail().getKey();
        System.out.println("Test Job " + jobKey);
    }
}
