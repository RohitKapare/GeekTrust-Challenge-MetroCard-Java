package com.example.geektrust.command;

import com.example.geektrust.exceptions.InvalidPassengerTypeException;
import com.example.geektrust.model.PassengerType;
import com.example.geektrust.service.JourneyService;

public class CheckInCommand implements Command {

  private final static int EXPECTED_TOKEN_COUNT = 4;
  private final static int CARD_ID_INDEX = 1;
  private final static int PASSENGER_TYPE_INDEX = 2;
  private final static int FROM_STATION_INDEX = 3;
  private final JourneyService journeyService;

  public CheckInCommand(JourneyService journeyService) {
    this.journeyService = journeyService;
  }

  @Override
  public void execute(String[] tokens) {
    if (tokens.length != EXPECTED_TOKEN_COUNT) {
      throw new IllegalArgumentException(
          "CHECK_IN command should be in format: CHECK_IN <CARD_ID> <PASSENGER_TYPE> <FROM_STATION> ");
    }
    String cardNumber = tokens[CARD_ID_INDEX];
    PassengerType type;
    try {
      type = PassengerType.valueOf(tokens[PASSENGER_TYPE_INDEX].toUpperCase());
    } catch (IllegalArgumentException e) {
      throw new InvalidPassengerTypeException("Invalid passenger type: " + tokens[2]);
    }
    String fromStation = tokens[FROM_STATION_INDEX].toUpperCase();
    journeyService.checkIn(cardNumber, type, fromStation);
  }
}
