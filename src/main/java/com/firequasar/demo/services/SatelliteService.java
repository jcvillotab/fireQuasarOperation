package com.firequasar.demo.services;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import com.firequasar.demo.entities.Satellite;
import com.firequasar.demo.entities.SatelliteData;

public interface SatelliteService {
  List<Satellite> getSatellites();
  Optional<SatelliteData> getSatelliteData(String name, String userId);
  void saveSatelliteData(SatelliteData satelliteData);
  void saveSatellite(Satellite satellite);
  Set<String> getSatelliteNames();
  List<SatelliteData> getSatelliteDataByUserId(String userId);
}
