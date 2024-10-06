package com.firequasar.demo.services;

import com.firequasar.demo.entities.dto.TopSecretRequest;
import com.firequasar.demo.entities.dto.TopSecretResponse;

public interface SecretService {
  TopSecretResponse getSecret(TopSecretRequest request);
}
