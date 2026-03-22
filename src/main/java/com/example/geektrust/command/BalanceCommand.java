package com.example.geektrust.command;

import com.example.geektrust.service.MetroCardService;

public class BalanceCommand implements Command {

  private final static int EXPECTED_TOKEN_COUNT = 3;
  private final static int CARD_ID_INDEX = 1;
  private final static int AMOUNT_INDEX = 2;
  private final MetroCardService metroCardService;

  public BalanceCommand(MetroCardService metroCardService) {
    this.metroCardService = metroCardService;
  }

  @Override
  public void execute(String[] tokens) {
    if (tokens.length != EXPECTED_TOKEN_COUNT) {
      throw new IllegalArgumentException(
          "BALANCE command should be in format: BALANCE <cardId> <amount>");
    }
    String cardNumber = tokens[CARD_ID_INDEX];
    int balance = Integer.parseInt(tokens[AMOUNT_INDEX]);
    metroCardService.setBalance(cardNumber, balance);
  }
}
