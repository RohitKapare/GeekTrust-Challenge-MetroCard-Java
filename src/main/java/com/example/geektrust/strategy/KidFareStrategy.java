package com.example.geektrust.strategy;

public class KidFareStrategy implements FareStrategy {

  @Override
  public int getBaseFare() {
    return 50;
  }

  @Override
  public int getDiscountedFare() {
    return (int) (getBaseFare() * 0.5);
  }

}
