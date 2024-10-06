package com.firequasar.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.firequasar.demo.entities.Satellite;

@Repository
public interface SatelliteRepository extends JpaRepository<Satellite, Integer> {
  
}
