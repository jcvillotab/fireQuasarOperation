package com.firequasar.demo.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.firequasar.demo.entities.Satellite;
import com.firequasar.demo.entities.dto.SatelliteDto;

@Mapper
public interface SatelliteMapper {
  @Mapping(target = "position", expression = "java(new double[] { satellite.getXPosition(), satellite.getYPosition() })")
  SatelliteDto toSatelliteDto(Satellite satellite);
}
