package com.weatherreport.sensors.service;

import com.weatherreport.sensors.exception.SensorAlreadyExistsException;
import com.weatherreport.sensors.exception.SensorDoesNotExistsException;
import com.weatherreport.sensors.model.AverageWeatherReport;
import com.weatherreport.sensors.model.SensorMetadata;
import com.weatherreport.sensors.model.SensorMetadatas;
import com.weatherreport.sensors.model.SensorMetrics;
import java.time.LocalDate;
import java.util.List;

public interface SensorService {

  SensorMetadatas getAllSensorsMetadata();

  SensorMetadatas getAllSensorMetadataByFilter(Integer sensorId);

  void addSensorsMetadata(SensorMetadata metadata) throws SensorAlreadyExistsException;

  void addSensorsMetrics(SensorMetrics sensorMetrics) throws SensorDoesNotExistsException;

  AverageWeatherReport getSensorMetrics(List<String> sensorIds, LocalDate startDate,
      LocalDate endDate);
}
