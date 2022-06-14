package com.example.quartzscheduler.util;

public enum MisfirePolicy {
    FIRE_NOW,
    IGNORE_MISFIRES,
    NEXT_WITH_EXISTING_COUNT,
    NEXT_WITH_REMAINING_COUNT,
    NOW_WITH_EXISTING_COUNT,
    NOW_WITH_REMAINING_COUNT,
    DO_NO_THING,
    FIRE_AND_PROCEED,
    DEFAULT
}
