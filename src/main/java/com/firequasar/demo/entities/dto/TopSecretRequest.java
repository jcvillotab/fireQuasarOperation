package com.firequasar.demo.entities.dto;

import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TopSecretRequest {
  private List<SatelliteMessageDto> satellites;
}
