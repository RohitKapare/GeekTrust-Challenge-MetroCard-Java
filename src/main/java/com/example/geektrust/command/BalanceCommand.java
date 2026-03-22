package com.example.geektrust.command;

import com.example.geektrust.service.MetroCardService;

public class BalanceCommand implements Command{

  private final MetroCardService metroCardService;

  public BalanceCommand(MetroCardService metroCardService) {
    this.metroCardService = metroCardService;
  }

  @Override
  public void execute(String[] tokens) {
    if(tokens.length != 3) {
      throw new IllegalArgumentException("BALANCE command should be in format: BALANCE <cardId> <amount>");
    }
    String cardNumber = tokens[1];
    int balance = Integer.parseInt(tokens[2]);
    metroCardService.setBalance(cardNumber, balance);
  }
}
