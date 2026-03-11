package com.example.geektrust.factory;

import com.example.geektrust.command.Command;
import com.example.geektrust.service.JourneyService;
import com.example.geektrust.service.MetroCardService;

public class CommandFactory {
  private final MetroCardService metroCardService;
  private final JourneyService journeyService;

  public CommandFactory(MetroCardService metroCardService, JourneyService journeyService) {
    this.metroCardService = metroCardService;
    this.journeyService = journeyService;
  }

  public Command getCommand(String commandName) {
    switch (commandName.toUpperCase()) {
      case "BALANCE": return new BalanceCommand(metroCardService);
      case "CHECK_IN": return new checkInCommand(journeyService);
      case "PRINT_SUMMARY": return new PrintSummaryCommand(journeyService);
      default: throw new IllegalArgumentException("Unknown command: " + commandName);
    }
  }
}
