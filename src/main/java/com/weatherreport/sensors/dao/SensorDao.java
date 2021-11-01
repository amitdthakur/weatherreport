package com.weatherreport.sensors.dao;

import com.weatherreport.sensors.exception.SensorAlreadyExistsException;
import com.weatherreport.sensors.model.AverageWeatherReport;
import com.weatherreport.sensors.model.SensorMetadata;
import com.weatherreport.sensors.model.SensorMetadatas;
import com.weatherreport.sensors.model.SensorMetrics;
import java.util.List;

public interface SensorDao {

  SensorMetadatas getAllSensorsMetadata();

  SensorMetadatas getAllSensorMetadataByFilter(Integer sensorId);

  void addSensorsMetadata(SensorMetadata metadata) throws SensorAlreadyExistsException;

  void addSensorsMetrics(SensorMetrics sensorMetrics);

  AverageWeatherReport getSensorMetrics(List<String> sensorIds, String startDate,
      String endDate);

  AverageWeatherReport getSensorMetrics(List<String> sensorIds);
}
