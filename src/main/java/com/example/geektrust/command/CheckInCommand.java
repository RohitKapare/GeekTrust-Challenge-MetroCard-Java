package com.example.geektrust.command;

import com.example.geektrust.exceptions.InvalidPassengerTypeException;
import com.example.geektrust.model.PassengerType;
import com.example.geektrust.service.JourneyService;

public class CheckInCommand implements Command {

  private final JourneyService journeyService;

  public CheckInCommand(JourneyService journeyService) {
    this.journeyService = journeyService;
  }

  @Override
  public void execute(String[] tokens) {
    if (tokens.length != 4) {
      throw new IllegalArgumentException(
          "CHECK_IN command should be in format: CHECK_IN <METROCARD_NUMBER> <PASSENGER_TYPE> <FROM_STATION> ");
    }
    String cardNumber = tokens[1];
    PassengerType type;
    try {
      type = PassengerType.valueOf(tokens[2].toUpperCase());
    } catch (IllegalArgumentException e) {
      throw new InvalidPassengerTypeException("Invalid passenger type: " + tokens[2]);
    }
    String fromStation = tokens[3].toUpperCase();
    journeyService.checkIn(cardNumber, type, fromStation);
  }
}
