package com.weatherreport.sensors.service;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.when;

import com.weatherreport.sensors.dao.SensorDao;
import com.weatherreport.sensors.exception.SensorDoesNotExistsException;
import com.weatherreport.sensors.model.SensorMetadatas;
import com.weatherreport.sensors.model.SensorMetrics;
import java.util.ArrayList;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class SensorServiceImplTest {

  @InjectMocks
  private SensorServiceImpl sensorService;

  @Mock
  private SensorDao sensorDao;

  @Test
  void addSensorsMetrics_IfSensorAlreadyExists_ThenSensorDoesNotExistsExceptionWillBeThrown() {
    //Test data preparation
    SensorMetrics sensorMetrics = new SensorMetrics();
    sensorMetrics.setId(1);
    SensorMetadatas sensorMetadatas = new SensorMetadatas();
    sensorMetadatas.setSensors(new ArrayList<>());
    //Mocking and calling function.
    when(sensorService.getAllSensorMetadataByFilter(sensorMetrics.getId()))
        .thenReturn(sensorMetadatas);
    assertThrows(SensorDoesNotExistsException.class, () -> {
      sensorService.addSensorsMetrics(sensorMetrics);
      fail("Test case should not reach here");
    });
  }
}
