package com.example.geektrust.command;

import com.example.geektrust.model.PassengerType;
import com.example.geektrust.service.JourneyService;

public class CheckInCommand implements Command {

  private final JourneyService journeyService;

  public CheckInCommand(JourneyService journeyService) {
    this.journeyService = journeyService;
  }

  @Override
  public void execute(String[] tokens) {
    String cardNumber = tokens[1];
    PassengerType type = PassengerType.valueOf(tokens[2].toUpperCase());
    String fromStation = tokens[3].toUpperCase();
    journeyService.checkIn(cardNumber, type, fromStation);
  }
}
