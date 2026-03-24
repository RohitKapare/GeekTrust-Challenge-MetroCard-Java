package com.example.geektrust.strategy;

public class AdultFareStrategy implements FareStrategy {

  private static final int ADULT_BASE_FARE = 200;

  @Override
  public int getBaseFare() {
    return ADULT_BASE_FARE;
  }

  @Override
  public int getDiscountedFare() {
    return (int) (ADULT_BASE_FARE * RETURN_DISCOUNT_RATE);
  }
}
