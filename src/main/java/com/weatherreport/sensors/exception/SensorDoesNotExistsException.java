package com.weatherreport.sensors.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SensorDoesNotExistsException extends Exception {

  private String message;
}
