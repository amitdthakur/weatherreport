package com.weatherreport.sensors.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Getter
public class SensorDoesNotExistsException extends Exception {

  private String message;
}
