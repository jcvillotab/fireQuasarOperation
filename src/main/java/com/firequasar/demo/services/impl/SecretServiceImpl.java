package com.firequasar.demo.services.impl;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.firequasar.demo.entities.dto.SatelliteMessageDto;
import com.firequasar.demo.entities.dto.TopSecretRequest;
import com.firequasar.demo.entities.dto.TopSecretResponse;
import com.firequasar.demo.services.LocationService;
import com.firequasar.demo.services.MessageService;
import com.firequasar.demo.services.SecretService;

@Service
public class SecretServiceImpl implements SecretService {

  @Autowired
  private LocationService locationService;

  @Autowired
  private MessageService messageService;

  @Override
  public TopSecretResponse getSecret(TopSecretRequest request) {
    
    double[] distances = request.getSatellites().stream()
      .mapToDouble(SatelliteMessageDto::getDistance)
      .toArray();

    List<String[]> messages = request.getSatellites().stream()
      .map(satellite -> satellite.getMessage().toArray(new String[0]))
      .collect(Collectors.toList());
  
    return TopSecretResponse.builder()
      .position(locationService.getLocation(distances))
      .message(messageService.getMessage(messages))
      .build();
  }
  
}
