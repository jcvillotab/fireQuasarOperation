package com.firequasar.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.firequasar.demo.entities.dto.TopSecretResponse;
import com.firequasar.demo.entities.dto.TopSecretSplitRequest;
import com.firequasar.demo.services.SecretService;
import com.firequasar.demo.utils.Constants;

import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/topsecret_split")
public class TopSecretSplitController {

  @Autowired
  private SecretService secretService;

  @PostMapping("/{satelliteName}")
  public ResponseEntity<?> postMethodName(@PathVariable String satelliteName,@RequestBody TopSecretSplitRequest request, @CookieValue(value = Constants.COOKIE_NAME, defaultValue = "") String userId) {
    secretService.saveSecret(request, satelliteName, userId);
    return ResponseEntity.ok().build();
  }
  
  @GetMapping("/")
  public ResponseEntity<TopSecretResponse> getMethodName(@CookieValue(value = Constants.COOKIE_NAME, defaultValue = "") String userId) {
      return ResponseEntity.ok(secretService.getSecret(userId));
  }
}
