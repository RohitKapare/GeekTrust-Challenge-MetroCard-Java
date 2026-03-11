package com.example.geektrust.strategy;

public class SeniorCitizenFareStrategy implements FareStrategy {

  @Override
  public double getBaseFare() {
    return 100.0;
  }

  @Override
  public double getDiscountedFare() {
    return getBaseFare() * 0.5;
  }

}
