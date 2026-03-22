package com.example.geektrust.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

import com.example.geektrust.model.MetroCard;
import com.example.geektrust.model.PassengerType;
import com.example.geektrust.model.Station;
import com.example.geektrust.repository.StationRepository;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class JourneyServiceTest {

  @Mock
  private MetroCardService metroCardService;

  @Mock
  private StationRepository stationRepository;

  private JourneyService journeyService;

  private Station centralStation;
  private Station airportStation;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.initMocks(this);
    journeyService = new JourneyServiceImpl(metroCardService, stationRepository);
    centralStation = new Station("CENTRAL");
    airportStation = new Station("AIRPORT");
  }

  @Test
  @DisplayName("Single trip from central for ADULT with not discount, full fare")
  public void singleTripAdultFromCentral() {
    MetroCard card = new MetroCard("MC1", 600);
    when(metroCardService.getCard("MC1")).thenReturn(card);
    when(metroCardService.rechargeIfRequired(card, 200)).thenReturn(0);
    when(stationRepository.findByName("CENTRAL")).thenReturn(centralStation);

    journeyService.checkIn("MC1", PassengerType.ADULT, "CENTRAL");

    assertEquals(200, centralStation.getTotalCollection());
    assertEquals(0, centralStation.getTotalDiscount());
    assertEquals(1, (int) centralStation.getPassengerCount().get(PassengerType.ADULT));
    assertEquals(400, card.getBalance());
  }

  @Test
  @DisplayName("Return trip by ADULT, 50% discount at destination station")
  public void returneeAdultGetDiscount() {
    MetroCard card = new MetroCard("MC1", 600);
    when(metroCardService.getCard("MC1")).thenReturn(card);
    when(metroCardService.rechargeIfRequired(any(MetroCard.class), anyInt())).thenReturn(0);
    when(stationRepository.findByName("CENTRAL")).thenReturn(centralStation);
    when(stationRepository.findByName("AIRPORT")).thenReturn(airportStation);

    journeyService.checkIn("MC1", PassengerType.ADULT, "CENTRAL");

    journeyService.checkIn("MC1", PassengerType.ADULT, "AIRPORT");

    assertEquals(100, airportStation.getTotalCollection());
    assertEquals(100, airportStation.getTotalDiscount());
  }

  @Test
  @DisplayName("Return trip removes journey record, discount again on fourth trip")
  public void noDiscountOnThirdTrip() {
    MetroCard card = new MetroCard("MC1", 1000);
    when(metroCardService.getCard("MC1")).thenReturn(card);
    when(metroCardService.rechargeIfRequired(any(MetroCard.class), anyInt())).thenReturn(0);
    when(stationRepository.findByName("CENTRAL")).thenReturn(centralStation);
    when(stationRepository.findByName("AIRPORT")).thenReturn(airportStation);

    journeyService.checkIn("MC1", PassengerType.ADULT, "CENTRAL");
    journeyService.checkIn("MC1", PassengerType.ADULT, "AIRPORT");
    journeyService.checkIn("MC1", PassengerType.ADULT, "CENTRAL");
    journeyService.checkIn("MC1", PassengerType.ADULT, "AIRPORT");

    assertEquals(200, airportStation.getTotalCollection());
    assertEquals(200, airportStation.getTotalDiscount());
  }

  @Test
  @DisplayName("Auto recharge should be done and service fee be included in station collection")
  public void autoRechargedServiceFeeAdded() {
    MetroCard card = new MetroCard("MC4", 50);
    when(metroCardService.getCard("MC4")).thenReturn(card);
    when(metroCardService.rechargeIfRequired(card, 200)).thenReturn(3);
    when(stationRepository.findByName("AIRPORT")).thenReturn(airportStation);
    when(metroCardService.rechargeIfRequired(card, 200)).thenReturn(3);

    journeyService.checkIn("MC4", PassengerType.ADULT, "AIRPORT");

    assertEquals(203, airportStation.getTotalCollection());
  }

  @Test
  @DisplayName("Passenger count should increase, even for return trips")
  public void passengerCountIncreaseEvenOnReturnTrips() {
    MetroCard card = new MetroCard("MC1", 1000);
    when(metroCardService.getCard("MC1")).thenReturn(card);
    when(metroCardService.rechargeIfRequired(any(MetroCard.class), anyInt())).thenReturn(0);
    when(stationRepository.findByName("CENTRAL")).thenReturn(centralStation);
    when(stationRepository.findByName("AIRPORT")).thenReturn(airportStation);

    journeyService.checkIn("MC1", PassengerType.ADULT, "CENTRAL");
    journeyService.checkIn("MC1", PassengerType.ADULT, "AIRPORT");

    assertEquals(1, (int) centralStation.getPassengerCount().get(PassengerType.ADULT));
    assertEquals(1, (int) airportStation.getPassengerCount().get(PassengerType.ADULT));
  }

  @Test
  @DisplayName("Passenger summary test to test output and sorting")
  public void passengerSummaryTest() {

    centralStation.addCollection(300);
    centralStation.incrementPassengerCount(PassengerType.ADULT);
    centralStation.incrementPassengerCount(PassengerType.SENIOR_CITIZEN);

    airportStation.addCollection(403);
    airportStation.addDiscount(100);
    airportStation.incrementPassengerCount(PassengerType.ADULT);
    airportStation.incrementPassengerCount(PassengerType.ADULT);
    airportStation.incrementPassengerCount(PassengerType.KID);
    airportStation.incrementPassengerCount(PassengerType.KID);

    when(stationRepository.findAll()).thenReturn(Arrays.asList(centralStation, airportStation));

    ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    PrintStream originalOut = System.out;
    System.setOut(new PrintStream(outContent));

    try {
      journeyService.printSummary();
    } finally {
      System.setOut(originalOut);
    }

    String n = System.lineSeparator();
    String expectedOutput =
      "TOTAL_COLLECTION CENTRAL 300 0" + n +
      "PASSENGER_TYPE_SUMMARY" + n +
      "ADULT 1" + n +
      "SENIOR_CITIZEN 1" + n +
      "TOTAL_COLLECTION AIRPORT 403 100" + n +
      "PASSENGER_TYPE_SUMMARY" + n +
      "ADULT 2" + n +
      "KID 2" + n;

    assertEquals(expectedOutput, outContent.toString());
  }
}


















