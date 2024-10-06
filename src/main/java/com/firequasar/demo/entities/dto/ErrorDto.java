package com.firequasar.demo.entities.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import java.time.Instant;

@Getter
@Setter
@Builder
public class ErrorDto {
  private String message;
  private Instant timestamp;
  
}
