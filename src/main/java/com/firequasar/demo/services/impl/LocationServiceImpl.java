package com.firequasar.demo.services.impl;

import java.util.Comparator;
import java.util.List;
import org.apache.commons.math3.fitting.leastsquares.LevenbergMarquardtOptimizer;
import org.springframework.stereotype.Service;
import com.firequasar.demo.entities.Coordinate;
import com.firequasar.demo.entities.Satellite;
import com.firequasar.demo.entities.dto.SatelliteMessageDto;
import com.firequasar.demo.services.LocationService;
import com.lemmingapex.trilateration.NonLinearLeastSquaresSolver;
import com.lemmingapex.trilateration.TrilaterationFunction;

@Service
public class LocationServiceImpl implements LocationService{

  public Coordinate getLocation(double[] distances, List<Satellite> satellites) {
    double[][] positions = getSatellitePositions(satellites);

    NonLinearLeastSquaresSolver solver = new NonLinearLeastSquaresSolver(
      new TrilaterationFunction(positions, distances),
      new LevenbergMarquardtOptimizer()
    );

    double[] result = solver.solve().getPoint().toArray();

    return Coordinate.builder().x(result[0]).y(result[1]).build();
  }

  public double[] getDistances(List<SatelliteMessageDto> satellites) {
    return satellites.stream()
      .sorted(Comparator.comparing(SatelliteMessageDto::getName))
      .mapToDouble(SatelliteMessageDto::getDistance)
      .toArray();
  }

  private double[][] getSatellitePositions(List<Satellite> satellites) {
    return satellites.stream()
      .map(satellite -> new double[] { satellite.getXPosition(), satellite.getYPosition() })
      .toArray(double[][]::new);
  }
  
}
