package com.example.geektrust.model;

import java.util.HashMap;
import java.util.Map;

public class Station {

  private final String stationName;
  private double totalCollection;
  private double totalDiscount;
  private final Map<PassengerType, Integer> passengerCount;

  public Station(String stationName, double totalDiscount, double totalCollection) {
    this.stationName = stationName;
    this.totalDiscount = 0;
    this.totalCollection = 0;
    this.passengerCount = new HashMap<>();
    for(PassengerType type : PassengerType.values()) {
      passengerCount.put(type, 0);
    }
  }

  public String getStationName() {
    return stationName;
  }

  public double getTotalCollection() {
    return totalCollection;
  }

  public double getTotalDiscount() {
    return totalDiscount;
  }

  public Map<PassengerType, Integer> getPassengerCount() {
    return passengerCount;
  }

  public void addCollection(double amount) {
    this.totalCollection += amount;
  }

  public void addDiscount(double amount) {
    this.totalDiscount += amount;
  }

  public void incrementPassengerCount(PassengerType type) {
    passengerCount.put(type, passengerCount.get(type) + 1);
  }
}
