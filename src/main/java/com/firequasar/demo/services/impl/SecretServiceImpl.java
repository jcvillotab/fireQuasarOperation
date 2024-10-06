package com.firequasar.demo.services.impl;

import java.util.List;
import java.util.Set;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import com.firequasar.demo.entities.Satellite;
import com.firequasar.demo.entities.SatelliteData;
import com.firequasar.demo.entities.dto.SatelliteMessageDto;
import com.firequasar.demo.entities.dto.TopSecretResponse;
import com.firequasar.demo.entities.dto.TopSecretSplitRequest;
import com.firequasar.demo.exceptions.LocationException;
import com.firequasar.demo.exceptions.MessageException;
import com.firequasar.demo.services.LocationService;
import com.firequasar.demo.services.MessageService;
import com.firequasar.demo.services.SatelliteService;
import com.firequasar.demo.services.SecretService;
import com.firequasar.demo.utils.mapper.SatelliteMapper;

@Service
public class SecretServiceImpl implements SecretService {

  @Autowired
  private LocationService locationService;

  @Autowired
  private MessageService messageService;

  @Autowired
  private SatelliteMapper satelliteMapper;

  @Autowired
  private SatelliteService satelliteService;

  public TopSecretResponse getSecret(List<SatelliteMessageDto> satellitesData)
      throws MessageException, LocationException {
    validatePayload(satellitesData);

    double[] distances = locationService.getDistances(satellitesData);

    List<Satellite> satellites = satelliteService.getSatellites();

    List<String[]> messages = messageService.convertMessages(satellitesData);

    return TopSecretResponse.builder()
        .position(locationService.getLocation(distances, satellites))
        .message(messageService.getMessage(messages))
        .build();
  }

  private void validatePayload(List<SatelliteMessageDto> satellites) throws MessageException, LocationException {
    if (CollectionUtils.isEmpty(satellites) || satellites.size() < 3) {
      throw new MessageException("There are not enough satellites to calculate the location");
    }

    if (satellites.stream().anyMatch(satellite -> satellite.getDistance() <= 0)) {
      throw new MessageException("There are satellites with invalid distances");
    }

    if (satellites.stream().anyMatch(satellite -> CollectionUtils.isEmpty(satellite.getMessage()))) {
      throw new MessageException("There are satellites with invalid messages");
    }

    Set<String> satelliteNames = satelliteService.getSatelliteNames();

    if (satellites.stream()
        .anyMatch(satellite -> !satelliteNames.contains(satellite.getName().toLowerCase()))) {
      throw new MessageException("There are satellites with invalid names");
    }
  }

  private void validateSplitPayload(TopSecretSplitRequest request, String satelliteName) throws MessageException {
    if (request.getDistance() <= 0) {
      throw new MessageException("Invalid distance");
    }

    if (request.getMessage() == null || request.getMessage().isEmpty()) {
      throw new MessageException("Invalid message");
    }

    Set<String> satelliteNames = satelliteService.getSatelliteNames();

    if (!satelliteNames.contains(satelliteName.toLowerCase())) {
      throw new MessageException("Invalid satellite name");
    }
  }

  public void saveSecret(TopSecretSplitRequest request, String satelliteName, String userId) throws MessageException {
    validateSplitPayload(request, satelliteName);

    SatelliteData satelliteData = satelliteMapper.toSatelliteData(request, satelliteName, userId);
    Optional<SatelliteData> existingData = satelliteService.getSatelliteData(satelliteName, userId);

    if (existingData.isPresent()) {
      satelliteData.setId(existingData.get().getId());
    }

    satelliteService.saveSatelliteData(satelliteData);
  }

  public TopSecretResponse getSecret(String userId) throws MessageException, LocationException {
    List<SatelliteMessageDto> satellitesData = satelliteService.getSatelliteDataByUserId(userId).stream()
        .map(satelliteMapper::toDto).toList();

    for (SatelliteMessageDto satellite : satellitesData) {
      System.out.println(satellite);
    }
    return getSecret(satellitesData);
  }

}
