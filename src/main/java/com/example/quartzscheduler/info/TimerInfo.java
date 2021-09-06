package com.example.quartzscheduler.info;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Builder
@Data
public class TimerInfo implements Serializable {
    private int totalFireCount;
    private int remainingFireCount;
    private boolean runForever;
    private long repeatIntervalMs;
    private long initialOffsetMs;
    private String callbackData;

}
