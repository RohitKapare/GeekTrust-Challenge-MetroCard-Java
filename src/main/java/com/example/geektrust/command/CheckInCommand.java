package com.example.geektrust.command;

import com.example.geektrust.service.MetroService;

public class CheckInCommand implements ICommand {

  private final MetroService metroService;

  public CheckInCommand(MetroService metroService) {
    this.metroService = metroService;
  }

  @Override
  public void execute(String[] tokens) {
    String cardId = tokens[1];
    String passengerType = tokens[2];
    String fromStation = tokens[3];

    metroService.processCheckIn(cardId, passengerType, fromStation);
  }
}
