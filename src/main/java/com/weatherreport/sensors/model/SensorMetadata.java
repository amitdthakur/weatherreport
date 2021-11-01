package com.weatherreport.sensors.model;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SensorMetadata {

  private Integer id;
  private String countryName;
  private String cityName;
  private LocalDateTime created;

}
