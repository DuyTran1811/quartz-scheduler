package com.example.quartzscheduler.service;

import com.example.quartzscheduler.info.TimerInfo;
import com.example.quartzscheduler.util.JobUtils;
import org.quartz.*;
import org.quartz.impl.calendar.HolidayCalendar;
import org.quartz.impl.matchers.GroupMatcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class SchedulerServiceImpl implements ISchedulerService {
    private static final Logger LOGGER = LoggerFactory.getLogger(SchedulerServiceImpl.class);
    private final Scheduler scheduler;

    public SchedulerServiceImpl(final Scheduler scheduler) {
        this.scheduler = scheduler;
    }

    @Override
    public <T extends Job> void createdSchedule(Class<T> jobClass, TimerInfo configInfo) {
        final JobDetail jobDetail = JobUtils.buildJobDetail(jobClass, configInfo);
        final Trigger trigger = JobUtils.buildTrigger(jobClass, configInfo);

        try {
            Date time = scheduler.scheduleJob(jobDetail, trigger);
            LOGGER.info("Was created at ! {}", time);
        } catch (SchedulerException exception) {
            LOGGER.error("Job cannot be created !", exception);
        }
    }

    @Override
    public List<TimerInfo> getAllRunningTimers() {
        try {
            return scheduler.getJobKeys(GroupMatcher.anyGroup())
                    .stream()
                    .map(jobKey -> {
                        try {
                            final JobDetail jobDetail = scheduler.getJobDetail(jobKey);
                            return (TimerInfo) jobDetail.getJobDataMap().get(jobKey.getName());
                        } catch (final SchedulerException e) {
                            LOGGER.error(e.getMessage(), e);
                            return null;
                        }
                    })
                    .filter(Objects::nonNull)
                    .collect(Collectors.toList());
        } catch (final SchedulerException e) {
            LOGGER.error(e.getMessage(), e);
            return Collections.emptyList();
        }
    }

    @Override
    public TimerInfo getRunningTimer(String timerId) {
        try {
            final JobDetail jobDetail = scheduler.getJobDetail(new JobKey(timerId));
            if (jobDetail == null) {
                LOGGER.error("Failed to find timer with ID '{}'", timerId);
                return null;
            }

            return (TimerInfo) jobDetail.getJobDataMap().get(timerId);
        } catch (final SchedulerException e) {
            LOGGER.error(e.getMessage(), e);
            return null;
        }
    }

    @Override
    public void updateTimer(String timerId, TimerInfo info) {
        try {
            final JobDetail jobDetail = scheduler.getJobDetail(new JobKey(timerId));
            if (jobDetail == null) {
                LOGGER.error("Failed to find timer with ID '{}'", timerId);
                return;
            }

            jobDetail.getJobDataMap().put(timerId, info);
            scheduler.addJob(jobDetail, true, true);
        } catch (final SchedulerException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }

    @Override
    public Boolean deleteJob(String keyJob) {
        try {
            return scheduler.deleteJob(new JobKey(keyJob));
        } catch (SchedulerException e) {
            LOGGER.error(e.getMessage(), e);
            return false;
        }
    }
}
