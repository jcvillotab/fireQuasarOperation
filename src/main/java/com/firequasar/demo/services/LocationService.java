package com.firequasar.demo.services;

import java.util.List;

import com.firequasar.demo.entities.Coordinate;
import com.firequasar.demo.entities.Satellite;
import com.firequasar.demo.entities.dto.SatelliteMessageDto;

public interface LocationService {
  Coordinate getLocation(double[] distances, List<Satellite> satellites);
  double[] getDistances(List<SatelliteMessageDto> satellites);
}
