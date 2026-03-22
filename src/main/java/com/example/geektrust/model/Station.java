package com.example.geektrust.model;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Station {

  private final String stationName;
  private int totalCollection;
  private int totalDiscount;
  private final Map<PassengerType, Integer> passengerCount;

  public Station(String stationName) {
    this.stationName = stationName;
    this.totalDiscount = 0;
    this.totalCollection = 0;
    this.passengerCount = new HashMap<>();
    for (PassengerType type : PassengerType.values()) {
      passengerCount.put(type, 0);
    }
  }

  public String getStationName() {
    return stationName;
  }

  public int getTotalCollection() {
    return totalCollection;
  }

  public int getTotalDiscount() {
    return totalDiscount;
  }

  public Map<PassengerType, Integer> getPassengerCount() {
    return Collections.unmodifiableMap(passengerCount);
  }

  public void addCollection(int amount) {
    this.totalCollection += amount;
  }

  public void addDiscount(int amount) {
    this.totalDiscount += amount;
  }

  public void incrementPassengerCount(PassengerType type) {
    passengerCount.put(type, passengerCount.get(type) + 1);
  }
}
