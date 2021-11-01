package com.weatherreport.sensors.controlleradvice;

import com.weatherreport.sensors.model.ExceptionMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RuntimeExceptionHandler extends ResponseEntityExceptionHandler {

  private static final Logger LOGGER = LoggerFactory
      .getLogger(RuntimeExceptionHandler.class);

  @ExceptionHandler(RuntimeException.class)
  public ResponseEntity<ExceptionMessage> handleRunTimeException(RuntimeException exception) {
    ExceptionMessage exceptionMessage = new ExceptionMessage();
    exceptionMessage.setMessage(HttpStatus.INTERNAL_SERVER_ERROR.name());
    exceptionMessage.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
    LOGGER.error("RuntimeException occurred ", exception);
    return ResponseEntity
        .status(HttpStatus.INTERNAL_SERVER_ERROR)
        .body(exceptionMessage);
  }
}
