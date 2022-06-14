package com.example.quartzscheduler.service;

import com.example.quartzscheduler.info.TimerInfo;
import org.quartz.Job;

import java.util.List;

public interface ISchedulerService {

    <T extends Job> void createdSchedule(final Class<T> jobClass, TimerInfo configInfo);

    List<TimerInfo> getAllRunningTimers();

    TimerInfo getRunningTimer(final String timerId);

    void updateTimer(final String timerId, final TimerInfo info);

    Boolean deleteJob(final String timerId);
}
