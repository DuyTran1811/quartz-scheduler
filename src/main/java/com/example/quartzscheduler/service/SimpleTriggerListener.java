package com.example.quartzscheduler.service;

import org.quartz.JobExecutionContext;
import org.quartz.Trigger;
import org.quartz.TriggerListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SimpleTriggerListener implements TriggerListener {
    private static final Logger LOGGER = LoggerFactory.getLogger(SimpleTriggerListener.class);
    private final ISchedulerService schedulerService;

    public SimpleTriggerListener(ISchedulerService schedulerService) {
        this.schedulerService = schedulerService;
    }

    @Override
    public String getName() {
        return "globalTrigger";
    }

    @Override
    public void triggerFired(Trigger trigger, JobExecutionContext context) {
        String jobName = trigger.getJobKey().getName();
        LOGGER.info("Job name: " + jobName + " is fired");

    }

    @Override
    public boolean vetoJobExecution(Trigger trigger, JobExecutionContext context) {
        return false;
    }

    @Override
    public void triggerMisfired(Trigger trigger) {
        String jobName = trigger.getJobKey().getName();
        LOGGER.info("Job name: " + jobName + " is misfired");
    }

    @Override
    public void triggerComplete(Trigger trigger, JobExecutionContext context,
                                Trigger.CompletedExecutionInstruction triggerInstructionCode) {
        String jobName = trigger.getJobKey().getName();
        LOGGER.info("Job name: " + jobName + " is completed");
    }
}
