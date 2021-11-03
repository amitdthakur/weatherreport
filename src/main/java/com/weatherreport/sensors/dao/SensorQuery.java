package com.weatherreport.sensors.dao;

public interface SensorQuery {

  String INSERT_SENSOR_META_DATA = "INSERT INTO sensorMetaData (sensorId, countryName, cityName,created) VALUES (:sensorId, :countryName, :cityName, CURRENT_TIMESTAMP)";

  String SELECT_ALL_SENSOR_META_DATA = "select sensorid,countryname,cityname,created from sensormetadata order by created desc";

  String SELECT_SENSOR_META_DATA_FROM_SENSOR_ID = "select sensorid,countryname,cityname,created from sensorMetaData where sensorId = :sensorId";
  
  String INSERT_SENSOR_METRICS =
      "INSERT INTO sensorMetrics(sensorId,temperature,humidity,windSpeed,pressure,lastUpdated) "
          + " VALUES (:sensorId, :temperature, :humidity,:windSpeed,:pressure, CURRENT_TIMESTAMP)";

  String SELECT_SENSOR_METRICS_FROM_SENSOR_IDS_START_END_DATE = "SELECT \n"
      + "    metrics.sensorId as sensorId,\n"
      + "    meta.countryName as countryName,\n"
      + "    meta.cityName as cityName,\n"
      + "    meta.created as created,\n"
      + "    AVG(metrics.temperature) as avgTemperature,\n"
      + "    AVG(metrics.humidity) as avgHumidity,\n"
      + "    AVG(metrics.windSpeed) as avgWindSpeed,\n"
      + "    AVG(metrics.pressure) as avgPressure,\n"
      + "    MAX(metrics.lastUpdated) as lastUpdated,\n"
      + "    count(metrics.lastUpdated) as days\n"
      + "FROM\n"
      + "    sensorMetaData AS meta\n"
      + "        INNER JOIN\n"
      + "    sensorMetrics AS metrics ON meta.sensorId = metrics.sensorId\n"
      + "    where metrics.sensorId in (:ids) "
      + " AND DATE(lastUpdated) BETWEEN :fromDate AND :endDate "
      + "GROUP BY metrics.sensorId";

  String SELECT_SENSOR_METRICS_ALL_DATA_FROM_START_END_DATE = "SELECT \n"
      + "    metrics.sensorId as sensorId,\n"
      + "    meta.countryName as countryName,\n"
      + "    meta.cityName as cityName,\n"
      + "    meta.created as created,\n"
      + "    AVG(metrics.temperature) as avgTemperature,\n"
      + "    AVG(metrics.humidity) as avgHumidity,\n"
      + "    AVG(metrics.windSpeed) as avgWindSpeed,\n"
      + "    AVG(metrics.pressure) as avgPressure,\n"
      + "    MAX(metrics.lastUpdated) as lastUpdated,\n"
      + "    count(metrics.lastUpdated) as days\n"
      + "FROM\n"
      + "    sensorMetaData AS meta\n"
      + "        INNER JOIN\n"
      + "    sensorMetrics AS metrics ON meta.sensorId = metrics.sensorId\n"
      + " AND DATE(lastUpdated) BETWEEN :fromDate AND :endDate "
      + "GROUP BY metrics.sensorId";

  String SELECT_SENSOR_ID_LAST_UPDATED_BY_SENSOR_IDS = "SELECT \n"
      + "    metrics.sensorId AS sensorId,\n"
      + "    MAX(metrics.lastUpdated) AS lastUpdated\n"
      + "FROM\n"
      + "    sensorMetaData AS meta\n"
      + "        INNER JOIN\n"
      + "    sensorMetrics AS metrics ON meta.sensorId = metrics.sensorId\n"
      + "WHERE\n"
      + "    metrics.sensorId IN (:sensorIds)\n"
      + "GROUP BY metrics.sensorId";

  String SELECT_ALL_SENSOR_ID_LAST_UPDATED_BY_SENSOR_ID = "SELECT \n"
      + "    metrics.sensorId AS sensorId,\n"
      + "    MAX(metrics.lastUpdated) AS lastUpdated\n"
      + "FROM\n"
      + "    sensorMetaData AS meta\n"
      + "        INNER JOIN\n"
      + "    sensorMetrics AS metrics ON meta.sensorId = metrics.sensorId\n"
      + "GROUP BY metrics.sensorId";

  String SELECT_SENSOR_METRICS_BASED_ON_ID_AND_DATE = " SELECT \n"
      + "    metrics.sensorId as sensorId,\n"
      + "    meta.countryName as countryName,\n"
      + "    meta.cityName as cityName,\n"
      + "    meta.created as created,\n"
      + "    AVG(metrics.temperature) as avgTemperature,\n"
      + "    AVG(metrics.humidity) as avgHumidity,\n"
      + "    AVG(metrics.windSpeed) as avgWindSpeed,\n"
      + "    AVG(metrics.pressure) as avgPressure,\n"
      + "    MAX(metrics.lastUpdated) as lastUpdated,\n"
      + "    count(metrics.lastUpdated) as days\n"
      + "FROM\n"
      + "    sensorMetaData AS meta\n"
      + "        INNER JOIN\n"
      + "    sensorMetrics AS metrics ON meta.sensorId = metrics.sensorId\n"
      + "    where metrics.sensorId = :sensorId\n"
      + "    and DATE(lastUpdated) =  :lastUpdatedDate \n"
      + "    GROUP BY metrics.sensorId;";

}
