package com.firequasar.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import com.firequasar.demo.entities.dto.TopSecretRequest;
import com.firequasar.demo.entities.dto.TopSecretResponse;
import com.firequasar.demo.services.SecretService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;


@RestController
@RequestMapping("/topsecret")
public class TopSecretController {

  @Autowired
  private SecretService secretService;

  @PostMapping
  public ResponseEntity<TopSecretResponse> getTopSecret(@RequestBody TopSecretRequest request) {
      return ResponseEntity.ok(secretService.getSecret(request.getSatellites()));
  }
  
}
