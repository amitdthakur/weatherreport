package com.weatherreport.sensors.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.weatherreport.sensors.model.AverageWeatherReport;
import com.weatherreport.sensors.model.SensorMetadatas;
import com.weatherreport.sensors.service.SensorService;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@ExtendWith(SpringExtension.class)
@WebMvcTest(SensorsController.class)
class SensorsControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private SensorService sensorService;


  @Test
  void getAllSensorsMetadata_IfServiceReturnsValidResult_ThenResponseCodeShouldBe200()
      throws Exception {
    SensorMetadatas sensorMetadatas = new SensorMetadatas();
    when(sensorService.getAllSensorsMetadata()).thenReturn(sensorMetadatas);
    mockMvc.perform(MockMvcRequestBuilders
        .get("/v1/sensors/metadata")
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk());
  }

  @Test
  void getSensorMetrics_IfSensorIdsIsALLAndMockedFunction_Then200OkShouldReturned()
      throws Exception {
    AverageWeatherReport averageWeatherReport = new AverageWeatherReport();
    List<String> sensorIds = new ArrayList<>();
    sensorIds.add("ALL");
    when(sensorService.getSensorMetrics(sensorIds, null, null))
        .thenReturn(averageWeatherReport);
    mockMvc.perform(MockMvcRequestBuilders
        .get("/v1/sensors/metrics")
        .param("sensorIds", "ALL")
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk());
  }

  @Test
  void getSensorMetrics_IfSensorIdsIsALLStartDateIsLessThan30DaysAndMockedFunction_Then400BadRequestShouldReturned()
      throws Exception {
    AverageWeatherReport averageWeatherReport = new AverageWeatherReport();
    List<String> sensorIds = new ArrayList<>();
    sensorIds.add("ALL");
    LocalDate startDate = LocalDate.of(2021, 9, 30);
    LocalDate endDate = LocalDate.now();
    when(sensorService.getSensorMetrics(sensorIds, startDate, endDate))
        .thenReturn(averageWeatherReport);
    mockMvc.perform(MockMvcRequestBuilders
        .get("/v1/sensors/metrics")
        .param("sensorIds", "ALL")
        .param("startDate", "2021-09-30")
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isBadRequest());
  }

}
