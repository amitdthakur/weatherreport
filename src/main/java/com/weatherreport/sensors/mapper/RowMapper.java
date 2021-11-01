package com.weatherreport.sensors.mapper;

import com.weatherreport.sensors.model.SensorMetadata;
import com.weatherreport.sensors.model.SensorMetricsAvg;
import java.sql.ResultSet;
import java.sql.SQLException;

public final class RowMapper {

  public static SensorMetricsAvg mapSensorMetricsAvg(ResultSet resultSet, int rowNum)
      throws SQLException {
    SensorMetricsAvg sensorMetricsAvg = new SensorMetricsAvg();
    sensorMetricsAvg.setAvgTemperature(resultSet.getFloat("avgTemperature"));
    sensorMetricsAvg.setAvgHumidity(resultSet.getInt("avgHumidity"));
    sensorMetricsAvg.setAvgWindSpeed(resultSet.getFloat("avgWindSpeed"));
    sensorMetricsAvg.setAvgPressure(resultSet.getFloat("avgPressure"));
    sensorMetricsAvg
        .setLastUpdated(resultSet.getTimestamp("lastUpdated").toLocalDateTime());
    sensorMetricsAvg.setDays(resultSet.getInt("days"));
    SensorMetadata sensorMetadata = new SensorMetadata();
    sensorMetadata.setId(resultSet.getInt("sensorId"));
    sensorMetadata.setCityName(resultSet.getString("cityName"));
    sensorMetadata.setCountryName(resultSet.getString("countryName"));
    sensorMetadata
        .setCreated(resultSet.getTimestamp("created").toLocalDateTime());
    sensorMetricsAvg.setMetadata(sensorMetadata);
    return sensorMetricsAvg;
  }

  public static SensorMetadata mapSensorMetaData(ResultSet resultSet, int rowNum)
      throws SQLException {
    return new SensorMetadata(
        resultSet.getInt("sensorId"),
        resultSet.getString("countryName"),
        resultSet.getString("cityName"),
        resultSet.getTimestamp("created").toLocalDateTime());
  }


  public static SensorMetricsAvg mapSensorMetricsAvgIfNoDateProvided(ResultSet resultSet,
      int rowNum)
      throws SQLException {
    SensorMetricsAvg sensorMetricsAvg = new SensorMetricsAvg();
    sensorMetricsAvg.setId(resultSet.getInt("sensorId"));
    sensorMetricsAvg
        .setLastUpdated(resultSet.getTimestamp("lastUpdated").toLocalDateTime());
    return sensorMetricsAvg;
  }

  private RowMapper() {

  }
}
