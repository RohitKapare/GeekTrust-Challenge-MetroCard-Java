package com.example.geektrust.service;

import com.example.geektrust.model.MetroCard;

public interface MetroCardService {

  void setBalance(String cardNumber, double balance);

  MetroCard getCard(String cardNumber);

  double rechargeIfRequired(MetroCard card, double requiredField);
}
