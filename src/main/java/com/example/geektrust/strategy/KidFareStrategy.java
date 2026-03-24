package com.example.geektrust.strategy;

public class KidFareStrategy implements FareStrategy {

  private static final int KID_BASE_FARE = 50;

  @Override
  public int getBaseFare() {
    return KID_BASE_FARE;
  }

  @Override
  public int getDiscountedFare() {
    return (int) (KID_BASE_FARE * RETURN_DISCOUNT_RATE);
  }

}
