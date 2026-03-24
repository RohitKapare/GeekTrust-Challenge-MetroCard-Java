package com.example.geektrust.factory;

import com.example.geektrust.model.PassengerType;
import com.example.geektrust.strategy.DefaultFareStrategy;
import com.example.geektrust.strategy.FareStrategy;

public class FareStrategyFactory {

  public static FareStrategy getStrategy(PassengerType passengerType) {
    return new DefaultFareStrategy(passengerType.getBaseFare());
  }
}
