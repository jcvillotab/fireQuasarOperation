package com.firequasar.demo.services;

import com.firequasar.demo.entities.dto.SatelliteMessageDto;
import com.firequasar.demo.entities.dto.TopSecretResponse;
import com.firequasar.demo.entities.dto.TopSecretSplitRequest;
import com.firequasar.demo.exceptions.LocationException;
import com.firequasar.demo.exceptions.MessageException;
import java.util.List;

public interface SecretService {
  TopSecretResponse getSecret(List<SatelliteMessageDto> satellites) throws MessageException, LocationException;
  TopSecretResponse getSecret(String userId) throws MessageException, LocationException;
  void saveSecret(TopSecretSplitRequest request, String satelliteName, String userId) throws MessageException;
}
