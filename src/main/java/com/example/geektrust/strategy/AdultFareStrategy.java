package com.example.geektrust.strategy;

public class AdultFareStrategy implements FareStrategy{

  @Override
  public double getBaseFare() {
    return 200.0;
  }

  @Override
  public double getDiscountedFare() {
    return getBaseFare() * 0.5;
  }
}
