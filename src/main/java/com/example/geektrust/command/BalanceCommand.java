package com.example.geektrust.command;

import com.example.geektrust.service.MetroCardService;

public class BalanceCommand implements Command{

  private final MetroCardService metroCardService;

  public BalanceCommand(MetroCardService metroCardService) {
    this.metroCardService = metroCardService;
  }

  @Override
  public void execute(String[] tokens) {
    String cardNumber = tokens[1];
    double balance = Double.parseDouble(tokens[2]);
    metroCardService.setBalance(cardNumber, balance);
  }
}
