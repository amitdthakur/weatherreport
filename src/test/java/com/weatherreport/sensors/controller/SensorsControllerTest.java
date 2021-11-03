package com.weatherreport.sensors.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.weatherreport.sensors.model.SensorMetadatas;
import com.weatherreport.sensors.service.SensorService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
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
    Mockito.when(sensorService.getAllSensorsMetadata()).thenReturn(sensorMetadatas);
    mockMvc.perform(MockMvcRequestBuilders
        .get("/v1/sensors/metadata")
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk());
  }


}
