package com.example.quartzscheduler.service;

import com.example.quartzscheduler.info.TimerInfo;
import org.quartz.Job;

import java.util.List;

public interface ISchedulerService {

    <T extends Job> boolean schedule(final Class<T> jobClass, final String idJobClass, int time);

    List<TimerInfo> getAllRunningTimers();

    TimerInfo getRunningTimer(final String timerId);

    void updateTimer(final String timerId, final TimerInfo info);

    Boolean deleteJob(final String timerId);
}
