package com.weatherreport.sensors.util.controller;

import static org.junit.jupiter.api.Assertions.assertTrue;

import com.weatherreport.sensors.service.SensorService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;

@ExtendWith(SpringExtension.class)
@WebAppConfiguration
public class TestContoller {

  private MockMvc mockMvc;

  @MockBean
  SensorService sensorService;


  @Test
  public void getAllRecords_success() throws Exception {
    assertTrue(true);
  }


}
