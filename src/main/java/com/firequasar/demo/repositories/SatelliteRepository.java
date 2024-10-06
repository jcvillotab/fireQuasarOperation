package com.firequasar.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.firequasar.demo.entities.Satellite;

public interface SatelliteRepository extends JpaRepository<Satellite, Integer> {
  
}
