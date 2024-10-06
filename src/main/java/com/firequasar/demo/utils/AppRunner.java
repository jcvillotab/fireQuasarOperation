package com.firequasar.demo.utils;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import com.firequasar.demo.entities.Satellite;
import com.firequasar.demo.repositories.SatelliteRepository;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class AppRunner implements ApplicationRunner{

  @Autowired
  SatelliteRepository satelliteRepository;

  @Override
  public void run(ApplicationArguments args) throws Exception {
    log.info("Bootstrapping data: ");
    if(satelliteRepository.count() > 0) {
      log.info("Data already bootstrapped");
      return;
    }
    Satellite kenobi = Satellite.builder()
      .name("kenobi")
      .xPosition(-500.0)
      .yPosition(-200.0)
      .build();

    Satellite skywalker = Satellite.builder()
      .name("skywalker")
      .xPosition(100.0)
      .yPosition(-100.0)
      .build();

    Satellite sato = Satellite.builder()
      .name("sato")
      .xPosition(500.0)
      .yPosition(100.0)
      .build();

    satelliteRepository.saveAll(List.of(kenobi, skywalker, sato));
  }

  
}
