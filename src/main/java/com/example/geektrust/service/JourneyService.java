package com.example.geektrust.service;

import com.example.geektrust.model.PassengerType;

public interface JourneyService {

  void checkIn(String cardNumber, PassengerType passengerType, String fromStation);

  void printSummary();
}
