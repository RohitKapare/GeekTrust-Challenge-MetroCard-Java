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
    int balance = Integer.parseInt(tokens[2]);
    metroCardService.setBalance(cardNumber, balance);
  }
}
