package br.com.perdeuachou.api.utils;

import java.time.Instant;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Calendar;

public class Utils {

    public static Period getWeekPeriod() {
        ZoneId defaultZoneId = ZoneId.systemDefault();

        Calendar c = Calendar.getInstance();
        c.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);

        Instant instant = c.toInstant();
        LocalDate domingo = instant.atZone(defaultZoneId).toLocalDate();

        return Period.between(domingo, LocalDate.now());
    }
}
