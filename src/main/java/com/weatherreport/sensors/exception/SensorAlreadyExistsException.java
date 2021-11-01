package com.weatherreport.sensors.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SensorAlreadyExistsException extends Exception {

  private String message;
}
