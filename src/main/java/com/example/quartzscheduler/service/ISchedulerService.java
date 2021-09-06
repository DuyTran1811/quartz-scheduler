package com.example.quartzscheduler.service;

import com.example.quartzscheduler.info.TimerInfo;
import org.quartz.Job;

import java.util.List;

public interface ISchedulerService {

    <T extends Job> void schedule(final Class<T> jobClass, final TimerInfo info);

    List<TimerInfo> getAllRunningTimers();

    TimerInfo getRunningTimer(final String timerId);

    void updateTimer(final String timerId, final TimerInfo info);

    Boolean deleteTimer(final String timerId);
}
