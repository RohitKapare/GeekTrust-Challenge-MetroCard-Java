package com.example.geektrust.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PassengerTypeTest {

  @Test
  @DisplayName("Base fare of ADULT should be 200")
  public void adultBaseFareShouldBe200() {
    assertEquals(200, PassengerType.ADULT.getBaseFare());
  }

  @Test
  @DisplayName("Base fare of KID should be 50")
  public void kidBaseFareShouldBe50() {
    assertEquals(50, PassengerType.KID.getBaseFare());
  }

  @Test
  @DisplayName("Base fare of SENIOR_CITIZEN should be 100")
  public void seniorCitizenBaseFareShouldBe100() {
    assertEquals(100, PassengerType.SENIOR_CITIZEN.getBaseFare());
  }

  @Test
  @DisplayName("ADULT discounted fare should be 50% of base fare, which is 100")
  public void adultDiscountedFareShouldBe100() {
    assertEquals(100, PassengerType.ADULT.getDiscountedFare());
  }

  @Test
  @DisplayName("KID discounted fare should be 50% of base fare, which is 25")
  public void kidDiscountedFareShouldBe25() {
    assertEquals(25, PassengerType.KID.getDiscountedFare());
  }

  @Test
  @DisplayName("SENIOR_CITIZEN discounted fare should be 50% of base fare, which is 50")
  public void seniorCitizenDiscountedFareShouldBe50() {
    assertEquals(50, PassengerType.SENIOR_CITIZEN.getDiscountedFare());
  }

}
