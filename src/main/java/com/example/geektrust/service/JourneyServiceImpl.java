package com.example.geektrust.service;

import com.example.geektrust.factory.FareStrategyFactory;
import com.example.geektrust.model.MetroCard;
import com.example.geektrust.model.PassengerType;
import com.example.geektrust.model.Station;
import com.example.geektrust.repository.StationRepository;
import com.example.geektrust.strategy.FareStrategy;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JourneyServiceImpl implements JourneyService {

  private static final double SERVICE_FEE_RATE = 0.02;

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
    Station station = stationRepository.findByName((fromStation));

    FareStrategy strategy = FareStrategyFactory.getStrategy(passengerType);
    double baseFare = strategy.getBaseFare();
    double discountedFare = strategy.getDiscountedFare();

    boolean isReturn = isReturnJourney(cardNumber, fromStation);
    double fare;
    if (isReturn) {
      fare = discountedFare;
      journeyTracker.remove(cardNumber);
    } else {
      fare = baseFare;
      journeyTracker.put(cardNumber, fromStation);
    }

    double serviceFee = metroCardService.rechargeIfRequired(card, fare);
    card.deduct(fare);

    station.addCollection(fare + serviceFee);
    station.addDiscount(baseFare - discountedFare);
    station.incrementPassengerCount(passengerType);

  }

  private boolean isReturnJourney(String cardNumber, String fromStation) {
    if (!journeyTracker.containsKey(cardNumber)) {
      return false;
    }
    String lastStation = journeyTracker.get(cardNumber);
    return !lastStation.equalsIgnoreCase(fromStation);
  }

  @Override
  public void printSummary() {
    List<Station> stations = stationRepository.findAll();
    for (Station station : stations) {
      System.out.println("TOTAL_COLLECTION " + station.getStationName()
          + " " + (int) station.getTotalCollection()
          + " " + (int) station.getTotalDiscount());
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
