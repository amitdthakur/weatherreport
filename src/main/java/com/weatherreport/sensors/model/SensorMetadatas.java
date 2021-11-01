package com.weatherreport.sensors.model;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SensorMetadatas {

  private List<SensorMetadata> sensors;
}
