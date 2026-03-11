package com.example.geektrust.strategy;

public class KidFareStrategy implements FareStrategy {

  @Override
  public double getBaseFare() {
    return 50.0;
  }

  @Override
  public double getDiscountedFare() {
    return getBaseFare() * 0.5;
  }

}
