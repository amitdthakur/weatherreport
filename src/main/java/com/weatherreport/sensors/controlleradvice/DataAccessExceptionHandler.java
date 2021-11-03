package com.weatherreport.sensors.controlleradvice;

import com.weatherreport.sensors.model.ExceptionMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class DataAccessExceptionHandler extends ResponseEntityExceptionHandler {

  private static final Logger LOGGER = LoggerFactory
      .getLogger(DataAccessExceptionHandler.class);

  @ExceptionHandler(DataAccessException.class)
  protected ResponseEntity<Object> handleDataAccessException(
      DataAccessException exception, WebRequest request) {
    ExceptionMessage exceptionMessage = new ExceptionMessage();
    exceptionMessage.setMessage(HttpStatus.INTERNAL_SERVER_ERROR.name());
    exceptionMessage.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
    exceptionMessage.setShortMessage(exception.getMessage());
    LOGGER.error("DataAccessException occurred ", exception);
    return ResponseEntity
        .status(HttpStatus.INTERNAL_SERVER_ERROR)
        .body(exceptionMessage);
  }
}

