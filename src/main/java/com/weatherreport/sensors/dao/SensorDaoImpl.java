package com.weatherreport.sensors.dao;

import static com.weatherreport.sensors.constant.Constants.YYYY_MM_DD;
import static com.weatherreport.sensors.util.TimeUtil.returnDateInGivenFormat;

import com.weatherreport.sensors.exception.SensorAlreadyExistsException;
import com.weatherreport.sensors.mapper.RowMapper;
import com.weatherreport.sensors.model.AverageWeatherReport;
import com.weatherreport.sensors.model.SensorMetadata;
import com.weatherreport.sensors.model.SensorMetadatas;
import com.weatherreport.sensors.model.SensorMetrics;
import com.weatherreport.sensors.model.SensorMetricsAvg;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

@Repository
public class SensorDaoImpl implements SensorDao, SensorQuery {

  private static final Logger LOGGER = LoggerFactory.getLogger(SensorDaoImpl.class);
  public static final String ALL = "ALL";

  private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

  @Autowired
  public SensorDaoImpl(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
    this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
  }

  @Override
  public SensorMetadatas getAllSensorsMetadata() {
    SensorMetadatas sensorMetadatas = new SensorMetadatas();
    List<SensorMetadata> sensorMetadataList = namedParameterJdbcTemplate
        .query(SELECT_ALL_SENSOR_META_DATA, RowMapper::mapSensorMetaData);
    sensorMetadatas.setSensors(sensorMetadataList);
    return sensorMetadatas;
  }

  @Override
  public SensorMetadatas getAllSensorMetadataByFilter(Integer sensorId) {
    SqlParameterSource sqlParameterSource = new MapSqlParameterSource("sensorId", sensorId);
    List<SensorMetadata> sensorMetadataList = namedParameterJdbcTemplate
        .query(SELECT_SENSOR_META_DATA_FROM_SENSOR_ID,
            sqlParameterSource, RowMapper::mapSensorMetaData);
    SensorMetadatas sensorMetadatas = new SensorMetadatas();
    sensorMetadatas.setSensors(sensorMetadataList);
    return sensorMetadatas;
  }

  @Override
  public void addSensorsMetadata(SensorMetadata metadata) throws SensorAlreadyExistsException {
    MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
    mapSqlParameterSource.addValue("sensorId", metadata.getId())
        .addValue("countryName", metadata.getCountryName())
        .addValue("cityName", metadata.getCityName());
    try {
      namedParameterJdbcTemplate
          .update(INSERT_SENSOR_META_DATA, mapSqlParameterSource);
    } catch (DuplicateKeyException duplicateKeyException) {
      throw new SensorAlreadyExistsException("SENSOR_ALREADY_EXISTS_EXCEPTION");
    }

  }

  @Override
  public void addSensorsMetrics(SensorMetrics sensorMetrics) {
    MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
    mapSqlParameterSource.addValue("sensorId", sensorMetrics.getId())
        .addValue("temperature", sensorMetrics.getTemperature())
        .addValue("humidity", sensorMetrics.getHumidity())
        .addValue("windSpeed", sensorMetrics.getWindSpeed())
        .addValue("pressure", sensorMetrics.getPressure());
    namedParameterJdbcTemplate
        .update(INSERT_SENSOR_METRICS, mapSqlParameterSource);
  }

  @Override
  public AverageWeatherReport getSensorMetrics(List<String> sensorIds, String startDate,
      String endDate) {
    //Added Query parameter
    SqlParameterSource sqlParameterSource = new MapSqlParameterSource("ids", sensorIds)
        .addValue("fromDate", startDate)
        .addValue("endDate", endDate);
    //Check if all required of specific metrics.
    String query;
    if (sensorIds.size() == 1 && ALL.equalsIgnoreCase(sensorIds.get(0))) {
      query = SELECT_SENSOR_METRICS_ALL_DATA_FROM_START_END_DATE;
    } else {
      query = SELECT_SENSOR_METRICS_FROM_SENSOR_IDS_START_END_DATE;
    }
    final AverageWeatherReport averageWeatherReport = new AverageWeatherReport();
    List<SensorMetricsAvg> sensorMetricsAvgList = namedParameterJdbcTemplate.query(
        query, sqlParameterSource, RowMapper::mapSensorMetricsAvg);
    averageWeatherReport.setSensorMetricsReports(sensorMetricsAvgList);
    return averageWeatherReport;
  }


  @Override
  public AverageWeatherReport getSensorMetrics(List<String> sensorIds) {
    final AverageWeatherReport averageWeatherReport = new AverageWeatherReport();
    SqlParameterSource sqlParameterSource = new MapSqlParameterSource("sensorIds", sensorIds);
    List<SensorMetricsAvg> sensorMetricsAvgList;
    if (sensorIds.size() == 1 && ALL.equalsIgnoreCase(sensorIds.get(0))) {
      sensorMetricsAvgList = namedParameterJdbcTemplate
          .query(SELECT_ALL_SENSOR_ID_LAST_UPDATED_BY_SENSOR_ID, RowMapper::mapSensorMetricsAvgIfNoDateProvided);
    } else {
      sensorMetricsAvgList = namedParameterJdbcTemplate.query(
          SELECT_SENSOR_ID_LAST_UPDATED_BY_SENSOR_IDS, sqlParameterSource,
          RowMapper::mapSensorMetricsAvgIfNoDateProvided);
    }
    List<SensorMetricsAvg> sensorMetricsAverageListAll = new ArrayList<>();
    List<SensorMetricsAvg> sensorMetricsAverageList;
    for (SensorMetricsAvg sensorMetricsAvgFromList : sensorMetricsAvgList) {
      sqlParameterSource = new MapSqlParameterSource("sensorId", sensorMetricsAvgFromList.getId())
          .addValue("lastUpdatedDate",
              returnDateInGivenFormat(sensorMetricsAvgFromList.getLastUpdated(), YYYY_MM_DD));
      sensorMetricsAverageList = namedParameterJdbcTemplate
          .query(SELECT_SENSOR_METRICS_BASED_ON_ID_AND_DATE, sqlParameterSource,
              RowMapper::mapSensorMetricsAvg);
      sensorMetricsAverageListAll.addAll(sensorMetricsAverageList);
    }
    averageWeatherReport.setSensorMetricsReports(sensorMetricsAverageListAll);
    return averageWeatherReport;
  }

}
