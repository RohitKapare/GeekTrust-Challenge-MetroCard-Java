package com.example.geektrust.factory;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.example.geektrust.model.PassengerType;
import com.example.geektrust.strategy.DefaultFareStrategy;
import com.example.geektrust.strategy.FareStrategy;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class FareStrategyFactoryTest {

  @Test
  @DisplayName("Should return DefaultFareStrategy with correct base fare 200 and discounted fare 100 for ADULT")
  public void shouldReturnDefaultFareStrategyForAdult() {
    FareStrategy strategy = FareStrategyFactory.getStrategy(PassengerType.ADULT);
    assertTrue(strategy instanceof DefaultFareStrategy);
    assertEquals(200, strategy.getBaseFare());
    assertEquals(100, strategy.getDiscountedFare());
  }

  @Test
  @DisplayName("Should return DefaultFareStrategy with correct base fare 50 and discounted fare 25 for KID")
  public void shouldReturnDefaultFareStrategyForKid() {
    FareStrategy strategy = FareStrategyFactory.getStrategy(PassengerType.KID);
    assertTrue(strategy instanceof DefaultFareStrategy);
    assertEquals(50, strategy.getBaseFare());
    assertEquals(25, strategy.getDiscountedFare());
  }

  @Test
  @DisplayName("Should return DefaultFareStrategy with correct base fare 100 and discounted fare 50 for SENIOR_CITIZEN")
  public void shouldReturnDefaultFareStrategyForSeniorCitizen() {
    FareStrategy strategy = FareStrategyFactory.getStrategy(PassengerType.SENIOR_CITIZEN);
    assertTrue(strategy instanceof DefaultFareStrategy);
    assertEquals(100, strategy.getBaseFare());
    assertEquals(50, strategy.getDiscountedFare());
  }
}
