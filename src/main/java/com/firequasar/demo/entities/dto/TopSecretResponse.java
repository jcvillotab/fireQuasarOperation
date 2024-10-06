package com.firequasar.demo.entities.dto;

import com.firequasar.demo.entities.Coordinate;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class TopSecretResponse {
  private Coordinate position;
  private String message;
}
