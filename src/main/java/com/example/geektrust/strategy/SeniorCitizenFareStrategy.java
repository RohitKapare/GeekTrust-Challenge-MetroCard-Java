package com.example.geektrust.strategy;

public class SeniorCitizenFareStrategy implements FareStrategy {

  private static final int SENIOR_CITIZEN_BASE_FARE = 100;

  @Override
  public int getBaseFare() {
    return SENIOR_CITIZEN_BASE_FARE;
  }

  @Override
  public int getDiscountedFare() {
    return (int) (SENIOR_CITIZEN_BASE_FARE * RETURN_DISCOUNT_RATE);
  }

}
