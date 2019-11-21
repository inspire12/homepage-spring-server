package com.inspire12.homepage.util;

import java.time.LocalDateTime;

public class TimeUtil {
    public static String timeToStr(LocalDateTime dateTime) {
        return dateTime.getYear() + "." + dateTime.getMonthValue() + "." + dateTime.getDayOfMonth();
    }
}
