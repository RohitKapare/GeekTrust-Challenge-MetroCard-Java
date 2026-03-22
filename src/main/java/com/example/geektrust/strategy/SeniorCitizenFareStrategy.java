package com.example.geektrust.strategy;

public class SeniorCitizenFareStrategy implements FareStrategy {

  @Override
  public int getBaseFare() {
    return 100;
  }

  @Override
  public int getDiscountedFare() {
    return (int) (getBaseFare() * RETURN_DISCOUNT_RATE);
  }

}
