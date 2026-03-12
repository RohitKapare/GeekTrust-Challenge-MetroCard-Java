package com.example.geektrust.service;

import com.example.geektrust.model.MetroCard;

public interface MetroCardService {

  void setBalance(String cardNumber, int balance);

  MetroCard getCard(String cardNumber);

  int rechargeIfRequired(MetroCard card, int requiredField);
}
