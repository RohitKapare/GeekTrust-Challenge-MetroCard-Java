package com.example.geektrust.strategy;

public class DefaultFareStrategy implements FareStrategy {

  private final int baseFare;

  public DefaultFareStrategy(int baseFare) {
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
