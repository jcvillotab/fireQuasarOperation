package com.firequasar.demo.services.impl;

import java.util.List;
import java.util.stream.Collectors;
import java.util.Comparator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import com.firequasar.demo.entities.dto.SatelliteMessageDto;
import com.firequasar.demo.entities.dto.TopSecretRequest;
import com.firequasar.demo.entities.dto.TopSecretResponse;
import com.firequasar.demo.exceptions.LocationException;
import com.firequasar.demo.exceptions.MessageException;
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
  public TopSecretResponse getSecret(TopSecretRequest request) throws MessageException, LocationException {
    validatePayload(request);

    double[] distances = request.getSatellites().stream()
      .sorted(Comparator.comparing(SatelliteMessageDto::getName))
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

  private void validatePayload(TopSecretRequest request)  throws MessageException, LocationException {
    if (CollectionUtils.isEmpty(request.getSatellites()) || request.getSatellites().size() < 3) {
      throw new MessageException("There are not enough satellites to calculate the location");
    }

    if (request.getSatellites().stream().anyMatch(satellite -> StringUtils.hasText(satellite.getName()))) {
      throw new MessageException("There are satellites with invalid names");
    }

    if(request.getSatellites().stream().anyMatch(satellite -> satellite.getDistance() <= 0)) {
      throw new MessageException("There are satellites with invalid distances");
    }

    if(request.getSatellites().stream().anyMatch(satellite -> CollectionUtils.isEmpty(satellite.getMessage()))) {
      throw new MessageException("There are satellites with invalid messages");
    }
    
  }
  
}
