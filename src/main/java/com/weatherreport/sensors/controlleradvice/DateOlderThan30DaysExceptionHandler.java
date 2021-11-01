package com.weatherreport.sensors.controlleradvice;

import com.weatherreport.sensors.exception.DateOlderThan30DaysException;
import com.weatherreport.sensors.model.ExceptionMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class DateOlderThan30DaysExceptionHandler extends ResponseEntityExceptionHandler {

  private static final Logger LOGGER = LoggerFactory
      .getLogger(DateOlderThan30DaysExceptionHandler.class);

  @ExceptionHandler(DateOlderThan30DaysException.class)
  protected ResponseEntity<Object> handleDateOlderThan30DaysException(
      DateOlderThan30DaysException exception, WebRequest request) {
    ExceptionMessage exceptionMessage = new ExceptionMessage();
    exceptionMessage.setMessage(HttpStatus.BAD_REQUEST.name());
    exceptionMessage.setStatusCode(HttpStatus.BAD_REQUEST.value());
    exceptionMessage.setShortMessage(exception.getMessage());
    LOGGER.error("DateOlderThan30DaysException occurred ", exception);
    return ResponseEntity
        .status(HttpStatus.BAD_REQUEST)
        .body(exceptionMessage);
  }
}

