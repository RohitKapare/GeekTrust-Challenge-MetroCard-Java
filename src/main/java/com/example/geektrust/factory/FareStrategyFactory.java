package com.example.geektrust.factory;

import com.example.geektrust.model.PassengerType;
import com.example.geektrust.strategy.AdultFareStrategy;
import com.example.geektrust.strategy.FareStrategy;
import com.example.geektrust.strategy.KidFareStrategy;
import com.example.geektrust.strategy.SeniorCitizenFareStrategy;

public class FareStrategyFactory {

  public static FareStrategy getStrategy(PassengerType passengerType) {
    switch (passengerType) {
      case ADULT:
        return new AdultFareStrategy();
      case SENIOR_CITIZEN:
        return new SeniorCitizenFareStrategy();
      case KID:
        return new KidFareStrategy();
      default:
        throw new IllegalArgumentException("Unknown passenger type: " + passengerType);
    }
  }
}
