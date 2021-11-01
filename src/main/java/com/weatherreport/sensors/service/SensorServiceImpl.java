package com.weatherreport.sensors.service;

import static com.weatherreport.sensors.constant.Constants.YYYY_MM_DD;
import static com.weatherreport.sensors.util.TimeUtil.returnDateInGivenFormat;

import com.weatherreport.sensors.dao.SensorDao;
import com.weatherreport.sensors.exception.SensorAlreadyExistsException;
import com.weatherreport.sensors.exception.SensorDoesNotExistsException;
import com.weatherreport.sensors.model.AverageWeatherReport;
import com.weatherreport.sensors.model.SensorMetadata;
import com.weatherreport.sensors.model.SensorMetadatas;
import com.weatherreport.sensors.model.SensorMetrics;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SensorServiceImpl implements SensorService {

  private static final Logger LOGGER = LoggerFactory.getLogger(SensorServiceImpl.class);

  private final SensorDao sensorDao;

  @Autowired
  public SensorServiceImpl(SensorDao sensorDao) {
    this.sensorDao = sensorDao;
  }

  @Override
  public SensorMetadatas getAllSensorsMetadata() {
    return sensorDao.getAllSensorsMetadata();
  }

  @Override
  public SensorMetadatas getAllSensorMetadataByFilter(Integer sensorId) {
    return sensorDao.getAllSensorMetadataByFilter(sensorId);
  }

  @Override
  public void addSensorsMetadata(SensorMetadata metadata) throws SensorAlreadyExistsException {
    sensorDao.addSensorsMetadata(metadata);
  }

  @Override
  public void addSensorsMetrics(SensorMetrics sensorMetrics) throws SensorDoesNotExistsException {
    //Check if given sensor exists or not if not then throws exception.
    if (getAllSensorMetadataByFilter(sensorMetrics.getId()).getSensors().isEmpty()) {
      throw new SensorDoesNotExistsException(
          "GIVEN_" + sensorMetrics.getId() + "SENSOR_ID_DOES_NOT_EXISTS");
    }
    sensorDao.addSensorsMetrics(sensorMetrics);
  }

  @Override
  public AverageWeatherReport getSensorMetrics(List<String> sensorIds, LocalDate startDate,
      LocalDate endDate) {
    if (Objects.isNull(startDate) && Objects.isNull(endDate)) {
      return sensorDao.getSensorMetrics(sensorIds);
    } else {
      return sensorDao.getSensorMetrics(sensorIds, returnDateInGivenFormat(startDate, YYYY_MM_DD),
          returnDateInGivenFormat(endDate, YYYY_MM_DD));
    }
  }

}
