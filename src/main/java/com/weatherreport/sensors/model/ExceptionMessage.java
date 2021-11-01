package com.weatherreport.sensors.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExceptionMessage {

  private String message;
  private int statusCode;
  private String shortMessage;
}
