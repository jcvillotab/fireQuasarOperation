package com.firequasar.demo.services.impl;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.firequasar.demo.entities.Satellite;
import com.firequasar.demo.entities.SatelliteData;
import com.firequasar.demo.repositories.SatelliteDataRepository;
import com.firequasar.demo.repositories.SatelliteRepository;
import com.firequasar.demo.services.SatelliteService;

@Service
public class SatelliteServiceImpl implements SatelliteService {
  
  @Autowired
  private SatelliteRepository satelliteRepository;

  @Autowired
  private SatelliteDataRepository satelliteDataRepository;

  public List<Satellite> getSatellites() {
    return satelliteRepository.findAll().stream().sorted(Comparator.comparing(Satellite::getName))
        .collect(Collectors.toList());
  }

  public Optional<SatelliteData> getSatelliteData(String name, String userId) {
    return satelliteDataRepository.findByNameAndUserId(name, userId);
  }

  public void saveSatelliteData(SatelliteData satelliteData) {
    satelliteDataRepository.save(satelliteData);
  }

  public void saveSatellite(Satellite satellite) {
    satelliteRepository.save(satellite);
  }

  public Set<String> getSatelliteNames() {
    return this.getSatellites().stream()
        .map(satellite -> satellite.getName())
        .collect(Collectors.toSet());
  }

  public List<SatelliteData> getSatelliteDataByUserId(String userId) {
    return satelliteDataRepository.findByUserId(userId);
  }

}
