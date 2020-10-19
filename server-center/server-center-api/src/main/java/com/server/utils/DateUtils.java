package com.server.utils;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

public class DateUtils {

    public static LocalDateTime date2LocalDateTime() {
        Date todayDate = new Date();

        LocalDateTime ldt = todayDate.toInstant()
                .atZone( ZoneId.systemDefault() )
                .toLocalDateTime();
        return ldt;
    }

    public static Date localDateTime2Date() {
        LocalDateTime localDateTime = LocalDateTime.now();

        Date date = Date.from( localDateTime.atZone( ZoneId.systemDefault()).toInstant());
        return date;
    }
}
