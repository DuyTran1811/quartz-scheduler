//package com.example.quartzscheduler.service.jobs;
//
//import com.example.quartzscheduler.service.ICustomerService;
//import org.quartz.Job;
//import org.quartz.JobExecutionContext;
//import org.quartz.JobKey;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//
//public class HelloWorldJob implements Job {
//    private static final Logger LOG = LoggerFactory.getLogger(HelloWorldJob.class);
//    @Autowired
//    protected ICustomerService personService;
//    private String idJob;
//
//    @Override
//    public void execute(JobExecutionContext context) {
//        JobKey jobKey = context.getJobDetail().getKey();
//        LOG.info("Remaining fire count is '{}'", jobKey + " id Job: " + idJob);
//        String id = idJob;
//        Person person = personService.finById(id);
//        person.setStatus(false);
//        personService.createNew(person);
//    }
//
//    public void setIdJob(String idJob) {
//        this.idJob = idJob;
//    }
//}
