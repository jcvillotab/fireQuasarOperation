package com.firequasar.demo.entities.dto;

import java.util.List;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TopSecretRequest {
  @Size(min = 3, message = "The satellites array must have at least 3 elements")
  private List<SatelliteMessageDto> satellites;
}
