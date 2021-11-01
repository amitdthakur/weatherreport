package com.weatherreport.sensors.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public final class TimeUtil {

  public static String returnDateInGivenFormat(LocalDate date, String format) {
    DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(format);
    return date.format(dateFormatter);
  }

  public static String returnDateInGivenFormat(LocalDateTime dateTime, String format) {
    DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(format);
    return dateTime.format(dateFormatter);
  }

  public static boolean checkIfStartDateIsNotOlderThan30Days(LocalDate currentDate, LocalDate startDate) {
    LocalDate currentDateMinus30Days = currentDate.minusDays(30);
    return !Objects.isNull(startDate) ? startDate.isBefore(currentDateMinus30Days) : false;
  }

  private TimeUtil() {

  }

}
