package com.example.geektrust.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class StationTest {

  private Station station;
  private final String stationName = "AIRPORT";

  @BeforeEach
  void setUp() {
    this.station = new Station(stationName);
  }

  @Test
  @DisplayName("Should return correct station name")
  public void shouldReturnCorrectStationName() {
    assertEquals("AIRPORT", station.getStationName());
  }

  @Test
  @DisplayName("Should return 0 as initial total collection")
  public void shouldReturnCorrectInitialTotalCollection() {
    assertEquals(0, station.getTotalCollection());
  }

  @Test
  @DisplayName("Multiple journey records should add up to total collection")
  public void multipleRecordJourneyCallsAddToTotalCollection() {
    station.recordJourney(PassengerType.ADULT, 200, 0);
    station.recordJourney(PassengerType.SENIOR_CITIZEN, 100, 0);
    station.recordJourney(PassengerType.KID, 50, 0);
    assertEquals(350, station.getTotalCollection());
  }

  @Test
  @DisplayName("Discounted journey record should be add to totalDisount")
  public void recordJourneyAddDiscountToTotalDiscount() {
    station.recordJourney(PassengerType.ADULT, 100, 100);
    assertEquals(100, station.getTotalDiscount());
  }

  @Test
  @DisplayName("Journey should increment passenger count for the given passenger type")
  public void shouldIncrementPassengerCountForGivenType() {
    station.recordJourney(PassengerType.ADULT, 200, 0);
    assertEquals(1, (int) station.getPassengerCount().get(PassengerType.ADULT));
  }

  @Test
  @DisplayName("Multiple journey by same passenger type should add to that types count")
  public void MultipleJourneyBySamePassengerTypeShouldAddToThatTypesCount() {
    station.recordJourney(PassengerType.ADULT, 200, 0);
    station.recordJourney(PassengerType.ADULT, 200, 0);
    assertEquals(2, (int) station.getPassengerCount().get(PassengerType.ADULT));
  }
}
