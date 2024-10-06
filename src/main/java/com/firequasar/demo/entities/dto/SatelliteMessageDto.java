package com.firequasar.demo.entities.dto;

import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SatelliteMessageDto {
  private String name;
  private List<String> message;
  private double distance;
}
