package com.example.geektrust.model;

public class Passenger {

  private final String metroCardNumber;
  private final PassengerType passengerType;

  public Passenger(String metroCardNumber, PassengerType passengerType) {
    this.metroCardNumber = metroCardNumber;
    this.passengerType = passengerType;
  }

  public String getMetroCardNumber() {
    return metroCardNumber;
  }

  public PassengerType getPassengerType() {
    return passengerType;
  }
}
