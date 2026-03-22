package com.example.geektrust.factory;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.example.geektrust.model.PassengerType;
import com.example.geektrust.strategy.AdultFareStrategy;
import com.example.geektrust.strategy.FareStrategy;
import com.example.geektrust.strategy.KidFareStrategy;
import com.example.geektrust.strategy.SeniorCitizenFareStrategy;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class FareStrategyFactoryTest {

  @Test
  @DisplayName("Should return adultFareStrategy with correct fares")
  public void shouldReturnAdultFareStrategy() {
    FareStrategy strategy = FareStrategyFactory.getStrategy(PassengerType.ADULT);
    assertTrue(strategy instanceof AdultFareStrategy);
    assertEquals(200, strategy.getBaseFare());
    assertEquals(100, strategy.getDiscountedFare());
  }

  @Test
  @DisplayName("Should return KidFareStrategy with correct fares")
  public void shouldReturnKidFareStrategy() {
    FareStrategy strategy = FareStrategyFactory.getStrategy(PassengerType.KID);
    assertTrue(strategy instanceof KidFareStrategy);
    assertEquals(50, strategy.getBaseFare());
    assertEquals(25, strategy.getDiscountedFare());
  }

  @Test
  @DisplayName("Should return SeniorCitizenFareStrategy with correct fares")
  public void shouldReturnSeniorCitizenFareStrategy() {
    FareStrategy strategy = FareStrategyFactory.getStrategy(PassengerType.SENIOR_CITIZEN);
    assertTrue(strategy instanceof SeniorCitizenFareStrategy);
    assertEquals(100, strategy.getBaseFare());
    assertEquals(50, strategy.getDiscountedFare());
  }
}
