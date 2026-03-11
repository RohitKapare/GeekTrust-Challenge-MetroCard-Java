package com.example.geektrust.command;

import com.example.geektrust.service.JourneyService;

public class PringSummaryCommand implements Command {

  private final JourneyService journeyService;

  public PringSummaryCommand(JourneyService journeyService) {
    this.journeyService = journeyService;
  }

  @Override
  public void execute(String[] tokens) {
    journeyService.printSummary();
  }
}
