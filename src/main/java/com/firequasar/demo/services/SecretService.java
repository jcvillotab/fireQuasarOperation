package com.firequasar.demo.services;

import com.firequasar.demo.entities.dto.TopSecretRequest;
import com.firequasar.demo.entities.dto.TopSecretResponse;
import com.firequasar.demo.exceptions.LocationException;
import com.firequasar.demo.exceptions.MessageException;

public interface SecretService {
  TopSecretResponse getSecret(TopSecretRequest request) throws MessageException, LocationException;
}
