package com.weatherreport.sensors.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@Getter
public class DateOlderThan30DaysException extends Exception {

  private String message;


}
