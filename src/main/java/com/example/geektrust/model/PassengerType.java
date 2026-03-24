package com.example.geektrust.model;

import com.example.geektrust.strategy.FareStrategy;

public enum PassengerType implements FareStrategy {
  ADULT(200),
  SENIOR_CITIZEN(100),
  KID(50);

  private final int baseFare;

  PassengerType(int baseFare) {
    this.baseFare = baseFare;
  }

  @Override
  public int getBaseFare() {
    return baseFare;
  }

  @Override
  public int getDiscountedFare() {
    return (int) (baseFare * RETURN_DISCOUNT_RATE);
  }
}
