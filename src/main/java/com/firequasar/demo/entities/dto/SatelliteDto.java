package com.firequasar.demo.entities.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class SatelliteDto {
  private String name;
  private double[] position;
}
