package com.firequasar.demo.repositories;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.firequasar.demo.entities.SatelliteData;

@Repository
public interface SatelliteDataRepository extends JpaRepository<SatelliteData, Integer> {
  Optional<SatelliteData> findByNameAndUserId(String name, String userId);
  List<SatelliteData> findByUserId(String userId);
}
