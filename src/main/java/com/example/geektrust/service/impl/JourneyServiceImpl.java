package com.example.geektrust.service.impl;

import com.example.geektrust.exceptions.ResourceNotFoundException;
import com.example.geektrust.model.MetroCard;
import com.example.geektrust.model.PassengerType;
import com.example.geektrust.model.Station;
import com.example.geektrust.repository.StationRepository;
import com.example.geektrust.service.JourneyService;
import com.example.geektrust.service.MetroCardService;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JourneyServiceImpl implements JourneyService {

  private final Map<String, String> journeyTracker = new HashMap<>();
  private final MetroCardService metroCardService;
  private final StationRepository stationRepository;

  public JourneyServiceImpl(MetroCardService metroCardService,
      StationRepository stationRepository) {
    this.metroCardService = metroCardService;
    this.stationRepository = stationRepository;
  }

  @Override
  public void checkIn(String cardNumber, PassengerType passengerType, String fromStation) {
    MetroCard card = metroCardService.getCard(cardNumber);
    Station station = stationRepository.findByName((fromStation)).orElseThrow(
        () -> new ResourceNotFoundException("Station not found for name: " + fromStation));

    boolean isReturn = isReturnJourney(cardNumber, fromStation);
    updateJourneyTracker(cardNumber, fromStation, isReturn);
    processJourney(card, station, passengerType, isReturn);
  }

  private boolean isReturnJourney(String cardNumber, String fromStation) {
    if (!journeyTracker.containsKey(cardNumber)) {
      return false;
    }
    String lastStation = journeyTracker.get(cardNumber);
    return !lastStation.equalsIgnoreCase(fromStation);
  }

  private void processJourney(MetroCard card, Station station, PassengerType passengerType,
      boolean isReturn) {
    int fare = isReturn ? passengerType.getDiscountedFare() : passengerType.getBaseFare();
    int discount = isReturn ? passengerType.getBaseFare() - passengerType.getDiscountedFare() : 0;
    int serviceFee = metroCardService.rechargeIfRequired(card, fare);
    card.deduct(fare);
    station.recordJourney(passengerType, fare + serviceFee, discount);
  }

  private void updateJourneyTracker(String cardNumber, String fromStation, boolean isReturn) {
    if (isReturn) {
      journeyTracker.remove(cardNumber);
    } else {
      journeyTracker.put(cardNumber, fromStation);
    }
  }

  @Override
  public void printSummary() {
    List<Station> stations = stationRepository.findAll();
    for (Station station : stations) {
      System.out.println("TOTAL_COLLECTION " + station.getStationName()
          + " " + station.getTotalCollection()
          + " " + station.getTotalDiscount());
      System.out.println("PASSENGER_TYPE_SUMMARY");

      station.getPassengerCount().entrySet().stream()
             .filter(e -> e.getValue() > 0)
             .sorted(
                 Map.Entry.<PassengerType, Integer>comparingByValue(Comparator.reverseOrder())
                          .thenComparing(e -> e.getKey().name())
             )
             .forEach(e -> System.out.println(e.getKey() + " " + e.getValue()));
    }
  }
}
