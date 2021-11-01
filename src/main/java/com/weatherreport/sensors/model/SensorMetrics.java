package com.weatherreport.sensors.model;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SensorMetrics {

  private Integer id;
  private Float temperature;
  private Integer humidity;
  private Float windSpeed;
  private Float pressure;
  private LocalDateTime lastUpdated;
}
