package com.weatherreport.sensors.util;

import static com.weatherreport.sensors.util.TimeUtil.checkIfStartDateIsNotOlderThan30Days;
import static com.weatherreport.sensors.util.TimeUtil.returnDateInGivenFormat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.time.LocalDateTime;
import org.junit.jupiter.api.Test;


class TimeUtilTest {

  @Test
  void returnDateInGivenFormat_IfInputIsLocalDate_ThenOutputDateShouldBeFormatted() {
    LocalDate localDate = LocalDate.of(2021, 10, 30);
    String formattedDate = returnDateInGivenFormat(localDate, "yyyy-MM-dd");
    assertEquals("2021-10-30", formattedDate);
  }


  @Test
  void returnDateTimeInGivenFormat_IfInputIsLocalDateTime_ThenOutputDateShouldBeFormatted() {
    LocalDateTime localDateTime = LocalDateTime.of(2021, 10, 30, 12, 12);
    String formattedDate = returnDateInGivenFormat(localDateTime, "yyyy-MM-dd");
    assertEquals("2021-10-30", formattedDate);
  }


  @Test
  void checkIfStartDateIsNotOlderThan30DaysTest_IfGivenLocalDateOlderThan30Days_ThenTrueShouldBeReturned() {
    LocalDate localDate = LocalDate.of(2021, 9, 30);
    assertTrue(checkIfStartDateIsNotOlderThan30Days(LocalDate.now(), localDate));
  }

  @Test
  void checkIfStartDateIsNotOlderThan30DaysTest_IfGivenLocalDateIsNotOlderThan30Days_ThenFalseShouldBeReturned() {
    LocalDate localDateTime = LocalDate.of(2021, 10, 31);
    assertFalse(checkIfStartDateIsNotOlderThan30Days(LocalDate.now(), localDateTime));
  }

  @Test
  void checkIfStartDateIsNotOlderThan30DaysTest_IfGivenLocalDateIsNull_ThenTrueShouldBeReturned() {
    assertFalse(checkIfStartDateIsNotOlderThan30Days(LocalDate.now(), null));
  }
}
