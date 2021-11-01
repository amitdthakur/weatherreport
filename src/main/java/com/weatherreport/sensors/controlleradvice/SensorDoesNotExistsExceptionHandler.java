package com.weatherreport.sensors.controlleradvice;

import com.weatherreport.sensors.exception.SensorAlreadyExistsException;
import com.weatherreport.sensors.exception.SensorDoesNotExistsException;
import com.weatherreport.sensors.model.ExceptionMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class SensorDoesNotExistsExceptionHandler extends ResponseEntityExceptionHandler {

  private static final Logger LOGGER = LoggerFactory
      .getLogger(SensorAlreadyExistsException.class);

  @ExceptionHandler(SensorDoesNotExistsException.class)
  public ResponseEntity<ExceptionMessage> handleSensorDoesNotExistsException(
      SensorDoesNotExistsException exception) {
    ExceptionMessage exceptionMessage = new ExceptionMessage();
    exceptionMessage.setMessage(HttpStatus.BAD_REQUEST.name());
    exceptionMessage.setStatusCode(HttpStatus.BAD_REQUEST.value());
    exceptionMessage.setShortMessage(exception.getMessage());
    LOGGER.error("handleSensorDoesNotExistsException occurred ", exception);
    return ResponseEntity
        .status(HttpStatus.BAD_REQUEST)
        .body(exceptionMessage);
  }
}
