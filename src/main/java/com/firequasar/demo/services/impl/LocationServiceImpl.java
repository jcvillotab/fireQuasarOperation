package com.firequasar.demo.services.impl;

import java.util.List;

import org.apache.commons.math3.fitting.leastsquares.LevenbergMarquardtOptimizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.firequasar.demo.entities.Coordinate;
import com.firequasar.demo.entities.Satellite;
import com.firequasar.demo.repositories.SatelliteRepository;
import com.firequasar.demo.services.LocationService;
import com.lemmingapex.trilateration.NonLinearLeastSquaresSolver;
import com.lemmingapex.trilateration.TrilaterationFunction;

@Service
public class LocationServiceImpl implements LocationService{

  @Autowired
  private SatelliteRepository satelliteRepository;

  @Override
  public Coordinate getLocation(double[] distances) {
    double[][] positions = getSatellitePositions();
    for (int i = 0; i < positions.length; i++) {
      System.out.println("Satellite " + i + " x: " + positions[i][0] + " y: " + positions[i][1]);
    }

    for (int i = 0; i < distances.length; i++) {
      System.out.println("Distance " + i + ": " + distances[i]);
    }
    NonLinearLeastSquaresSolver solver = new NonLinearLeastSquaresSolver(
      new TrilaterationFunction(positions, distances),
      new LevenbergMarquardtOptimizer()
    );

    double[] result = solver.solve().getPoint().toArray();

    return Coordinate.builder().x(result[0]).y(result[1]).build();
  }

  private double[][] getSatellitePositions() {
    return satelliteRepository.findAll().stream()
      .map(satellite -> new double[] { satellite.getXPosition(), satellite.getYPosition() })
      .toArray(double[][]::new);
  }
  
  @Override
  public List<Satellite> getSatellites() {
    return satelliteRepository.findAll();
  }
}
