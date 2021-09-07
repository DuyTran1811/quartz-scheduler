package com.example.quartzscheduler.util;

import org.quartz.*;

public final class TimerUtils {

    private TimerUtils() {
    }

    public static JobDetail buildJobDetail(final Class<? extends Job> jobClass, final String idJobClass) {
        final JobDataMap jobDataMap = new JobDataMap();
        String idJob = "idJob";
        jobDataMap.put(idJob, idJobClass);

        return JobBuilder
                .newJob(jobClass)
                .withIdentity(jobClass.getSimpleName())
                .setJobData(jobDataMap)
                .build();
    }

    public static Trigger buildTrigger(final Class<? extends Job> jobClass, final Integer time) {
        SimpleScheduleBuilder builder = SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(time);

        if (time == 0) {
            builder = builder.repeatForever();
        } else {
            builder = builder.withRepeatCount(time - 1);
        }

        return TriggerBuilder
                .newTrigger()
                .withIdentity(jobClass.getSimpleName())
                .withSchedule(builder)
                .build();
    }
}
