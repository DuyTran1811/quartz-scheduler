package com.example.quartzscheduler.info;

import com.example.quartzscheduler.util.DaysOfWeek;
import com.example.quartzscheduler.util.MisfirePolicy;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TimerInfo implements Serializable {
    private String jobName;
    private int totalFireCount;
    private int repeatIntervalMs;
    private boolean runForever;
    private boolean saveHistory;
    private String description;
    private boolean requestRecovery = false;
    private MisfirePolicy misfirePolicy;
    @DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
    private Date startTime;
    @DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
    private Date endTime;
    @DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
    private Date cronSchedule;
    @DateTimeFormat(pattern = "HH:mm")
    private LocalDateTime time;
    private String daysOfWeek;

    public TimerInfo(String jobName, int totalFireCount, int repeatIntervalMs, boolean runForever, boolean saveHistory, String description, boolean requestRecovery, MisfirePolicy misfirePolicy) {
        this.jobName = jobName;
        this.totalFireCount = totalFireCount;
        this.repeatIntervalMs = repeatIntervalMs;
        this.runForever = runForever;
        this.saveHistory = saveHistory;
        this.description = description;
        this.requestRecovery = requestRecovery;
        this.misfirePolicy = misfirePolicy;
    }

    public String buildFromDayToDay(DaysOfWeek fromDay, DaysOfWeek toDay) {
        return fromDay.getName() + "-" + toDay.getName();
    }

    public String buildInTheDay(DaysOfWeek fromDay, DaysOfWeek toDay) {
        return fromDay.getName() + "," + toDay.getName();
    }
}