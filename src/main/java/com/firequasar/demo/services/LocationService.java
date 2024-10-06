package com.firequasar.demo.services;

import java.util.List;

import com.firequasar.demo.entities.Coordinate;
import com.firequasar.demo.entities.Satellite;

public interface LocationService {
  Coordinate getLocation(double[] distances);
  List<Satellite> getSatellites();
}
