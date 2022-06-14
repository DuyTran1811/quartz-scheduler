package com.example.quartzscheduler.util;

import com.example.quartzscheduler.info.TimerInfo;
import org.quartz.*;
import org.springframework.scheduling.SchedulingException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.TimeZone;

import static org.quartz.JobBuilder.newJob;

public final class JobUtils {

    private JobUtils() {
    }

    public static JobDetail buildJobDetail(final Class<? extends Job> jobClass, TimerInfo configInfo) {
        JobDataMap jobDataMap = new JobDataMap();
        jobDataMap.put(jobClass + jobClass.getSimpleName(), jobClass.getName());
        return newJob(jobClass)
                .withIdentity(configInfo.getJobName(), jobClass.getSimpleName())
                .setJobData(jobDataMap)
                .storeDurably(configInfo.isSaveHistory())
                .requestRecovery(configInfo.isRequestRecovery())
                .withDescription(configInfo.getDescription())
                .build();
    }

    public static Trigger buildTrigger(final Class<? extends Job> jobClass, TimerInfo configInfo) {
        SimpleScheduleBuilder builder = SimpleScheduleBuilder.simpleSchedule();
        builder.withIntervalInMilliseconds(configInfo.getRepeatIntervalMs());
        if (configInfo.isRunForever())
            builder = builder.repeatForever();
        else
            builder = builder.withRepeatCount(configInfo.getTotalFireCount() - 1);
        switch (configInfo.getMisfirePolicy()) {
            case DEFAULT:
                builder.build();
                break;
            case FIRE_NOW:
                builder.withMisfireHandlingInstructionFireNow();
                break;
            case IGNORE_MISFIRES:
                builder.withMisfireHandlingInstructionIgnoreMisfires();
                break;
            case NOW_WITH_EXISTING_COUNT:
                builder.withMisfireHandlingInstructionNextWithExistingCount();
                break;
            case NEXT_WITH_EXISTING_COUNT:
                builder.withMisfireHandlingInstructionNextWithRemainingCount();
                break;
            case NOW_WITH_REMAINING_COUNT:
                builder.withMisfireHandlingInstructionNowWithExistingCount();
                break;
            case NEXT_WITH_REMAINING_COUNT:
                builder.withMisfireHandlingInstructionNowWithRemainingCount();
                break;
            default:
                throw new SchedulingException("The task misfire policy " + configInfo.getMisfirePolicy() + "cannot be used in simple schedule tasks");
        }
        if (configInfo.getCronSchedule() != null) {
            String[] convertTime = configInfo.getTime().toString().split("[/ :]+");
            String day = configInfo.getDaysOfWeek() == null ? "" : configInfo.getDaysOfWeek();
            String cronExpression = String.format("0 %s %s ? * %s", convertTime[1], convertTime[0], day);
            CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(cronExpression)
                    .inTimeZone(TimeZone.getTimeZone("Asia/Ho_Chi_Minh"));
            switch (configInfo.getMisfirePolicy()) {
                case IGNORE_MISFIRES:
                    scheduleBuilder.withMisfireHandlingInstructionIgnoreMisfires();
                    break;
                case DO_NO_THING:
                    scheduleBuilder.withMisfireHandlingInstructionDoNothing();
                    break;
                case FIRE_AND_PROCEED:
                    scheduleBuilder.withMisfireHandlingInstructionFireAndProceed();
                    break;
                default:
                    throw new SchedulingException("The task misfire policy " + configInfo.getMisfirePolicy() + "cannot be used in simple schedule tasks");
            }
            return TriggerBuilder.newTrigger()
                    .withIdentity(configInfo.getJobName(), jobClass.getSimpleName())
                    .withSchedule(scheduleBuilder)
                    .build();
        }

        if (configInfo.getStartTime() == null || configInfo.getEndTime() == null) {
            return TriggerBuilder.newTrigger()
                    .withIdentity(configInfo.getJobName(), jobClass.getSimpleName())
                    .withSchedule(builder)
                    .build();
        } else {
            return TriggerBuilder.newTrigger()
                    .withIdentity(configInfo.getJobName(), jobClass.getSimpleName())
                    .withSchedule(builder)
                    .startAt(configInfo.getStartTime())
                    .endAt(configInfo.getEndTime())
                    .build();
        }

    }
}
