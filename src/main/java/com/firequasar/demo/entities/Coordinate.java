package com.firequasar.demo.entities;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class Coordinate {
  private double x;
  private double y;
}
