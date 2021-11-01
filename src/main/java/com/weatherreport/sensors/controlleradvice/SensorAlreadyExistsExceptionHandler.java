package com.weatherreport.sensors.controlleradvice;

import com.weatherreport.sensors.exception.DateOlderThan30DaysException;
import com.weatherreport.sensors.exception.SensorAlreadyExistsException;
import com.weatherreport.sensors.model.ExceptionMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class SensorAlreadyExistsExceptionHandler extends ResponseEntityExceptionHandler {

  private static final Logger LOGGER = LoggerFactory
      .getLogger(SensorAlreadyExistsException.class);

  @ExceptionHandler(SensorAlreadyExistsException.class)
  public ResponseEntity<ExceptionMessage> handleSensorExistsException(
      SensorAlreadyExistsException exception) {
    ExceptionMessage exceptionMessage = new ExceptionMessage();
    exceptionMessage.setMessage(HttpStatus.INTERNAL_SERVER_ERROR.name());
    exceptionMessage.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
    exceptionMessage.setShortMessage(exception.getMessage());
    LOGGER.error("handleSensorExistsException occurred ", exception);
    return ResponseEntity
        .status(HttpStatus.INTERNAL_SERVER_ERROR)
        .body(exceptionMessage);
  }
}
