package com.weatherreport.sensors.controller;

import static com.weatherreport.sensors.constant.Constants.YYYY_MM_DD;
import static com.weatherreport.sensors.util.TimeUtil.checkIfStartDateIsNotOlderThan30Days;

import com.weatherreport.sensors.exception.DateOlderThan30DaysException;
import com.weatherreport.sensors.exception.SensorAlreadyExistsException;
import com.weatherreport.sensors.exception.SensorDoesNotExistsException;
import com.weatherreport.sensors.model.AverageWeatherReport;
import com.weatherreport.sensors.model.SensorMetadata;
import com.weatherreport.sensors.model.SensorMetadatas;
import com.weatherreport.sensors.model.SensorMetrics;
import com.weatherreport.sensors.service.SensorService;
import java.time.LocalDate;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SensorsController {


  private static final Logger LOGGER = LoggerFactory.getLogger(SensorsController.class);


  private final SensorService sensorService;

  @Autowired
  public SensorsController(SensorService sensorService) {
    this.sensorService = sensorService;
  }

  @GetMapping("v1/sensors/metadata")
  public SensorMetadatas getAllSensorsMetadata() {
    return sensorService.getAllSensorsMetadata();
  }


  @PostMapping("v1/sensors/metadata")
  public ResponseEntity<String> addSensorsMetadata(@RequestBody SensorMetadata sensorMetadata)
      throws SensorAlreadyExistsException {
    sensorService.addSensorsMetadata(sensorMetadata);
    return new ResponseEntity<>("SensorCreated", HttpStatus.CREATED);
  }


  @PostMapping("v1/sensors/metrics")
  public ResponseEntity<String> addSensorsMetrics(@RequestBody SensorMetrics sensorMetrics)
      throws SensorDoesNotExistsException {
    sensorService.addSensorsMetrics(sensorMetrics);
    return new ResponseEntity<>("MetricsCreated", HttpStatus.CREATED);
  }

  @GetMapping("v1/sensors/metrics")
  public AverageWeatherReport getSensorMetrics(
      @RequestParam(defaultValue = "ALL") List<String> sensorIds,
      @RequestParam(required = false) @DateTimeFormat(pattern = YYYY_MM_DD) LocalDate endDate,
      @RequestParam(required = false) @DateTimeFormat(pattern = YYYY_MM_DD) LocalDate startDate)
      throws DateOlderThan30DaysException {
    LocalDate currentDate = LocalDate.now();
    if (checkIfStartDateIsNotOlderThan30Days(currentDate, startDate)) {
      LOGGER.info("Start date is older than current date by 30 days currentDate={} startDate={}",
          currentDate, startDate);
      throw new DateOlderThan30DaysException("START_DATE_IS_OLDER_THAN_CURRENT_DATE_BY_30_DAYS");
    } else {
      return sensorService.getSensorMetrics(sensorIds, startDate, endDate);
    }
  }

}
