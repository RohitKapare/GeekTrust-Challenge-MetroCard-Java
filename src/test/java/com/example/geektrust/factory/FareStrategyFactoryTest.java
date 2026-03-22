package com.example.geektrust.factory;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.example.geektrust.model.PassengerType;
import com.example.geektrust.strategy.AdultFareStrategy;
import com.example.geektrust.strategy.FareStrategy;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class FareStrategyFactoryTest {

  @Test
  @DisplayName("Should return respective fare strategy for passed passenger type")
  public void shouldReturnRespectiveStrategy() {
    FareStrategy strategy = FareStrategyFactory.getStrategy(PassengerType.ADULT);
    assertTrue(strategy instanceof AdultFareStrategy);
    assertEquals(200, strategy.getBaseFare());
  }
}
