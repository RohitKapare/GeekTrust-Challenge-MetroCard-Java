package com.example.geektrust.strategy;

public interface FareStrategy {

  double RETURN_DISCOUNT_RATE = 0.5;

  int getBaseFare();

  int getDiscountedFare();
}
