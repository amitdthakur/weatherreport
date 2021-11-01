package com.weatherreport.sensors.model;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SensorMetricsAvg {

  private Integer id;
  private Float avgTemperature;
  private Integer avgHumidity;
  private Float avgWindSpeed;
  private Float avgPressure;
  private LocalDateTime lastUpdated;
  private Integer days;
  private SensorMetadata metadata;
}
