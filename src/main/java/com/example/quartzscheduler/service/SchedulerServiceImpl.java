package com.example.quartzscheduler.service;

import com.example.quartzscheduler.info.TimerInfo;
import com.example.quartzscheduler.util.TimerUtils;
import org.quartz.*;
import org.quartz.impl.matchers.GroupMatcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.Collections;
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
    public <T extends Job> boolean schedule(Class<T> jobClass, String idJobClass, int time) {

        final JobDetail jobDetail = TimerUtils.buildJobDetail(jobClass, idJobClass);
        final Trigger trigger = TimerUtils.buildTrigger(jobClass, time);

        try {
            scheduler.scheduleJob(jobDetail, trigger);
            return true;
        } catch (SchedulerException e) {
            LOGGER.error("Không Thể Tạo Job Có Job Đã Tồn Tại Hoặc Job Này Đang Chạy !", e);
            return false;
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
    public Boolean deleteJob(String timerId) {
        try {
            return scheduler.deleteJob(new JobKey(timerId));
        } catch (SchedulerException e) {
            LOGGER.error(e.getMessage(), e);
            return false;
        }
    }

    @PostConstruct
    public void init() {
        try {
            scheduler.start();
//            scheduler.getListenerManager().addTriggerListener(new SimpleTriggerListener(this));
        } catch (SchedulerException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }

    @PreDestroy
    public void preDestroy() {
        try {
            scheduler.shutdown();
        } catch (SchedulerException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }
}
