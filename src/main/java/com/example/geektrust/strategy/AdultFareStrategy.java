package com.example.geektrust.strategy;

public class AdultFareStrategy implements FareStrategy {

  @Override
  public int getBaseFare() {
    return 200;
  }

  @Override
  public int getDiscountedFare() {
    return (int) (getBaseFare() * RETURN_DISCOUNT_RATE);
  }
}
