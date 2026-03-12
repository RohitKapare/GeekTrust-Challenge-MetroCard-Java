package com.example.geektrust.service;

import com.example.geektrust.model.MetroCard;
import com.example.geektrust.repository.MetroCardRepository;

public class MetroCardServiceImpl implements MetroCardService {

  private final MetroCardRepository metroCardRepository;

  public MetroCardServiceImpl(MetroCardRepository metroCardRepository) {
    this.metroCardRepository = metroCardRepository;
  }

  @Override
  public void setBalance(String cardNumber, double balance) {
    MetroCard card = new MetroCard(cardNumber, balance);
    metroCardRepository.save(card);
  }

  @Override
  public MetroCard getCard(String cardNumber) {
    MetroCard card = metroCardRepository.findById(cardNumber);
    if(card == null) {
      throw new IllegalArgumentException("MetroCard not found for card number: " + cardNumber);
    }
    return card;
  }

  @Override
  public double rechargeIfRequired(MetroCard card, double requiredFare) {
    double neededRecharge = requiredFare - card.getBalance();
    if(neededRecharge <= 0) {
      return 0;
    }
    return Math.ceil(neededRecharge * 0.02);
  }
}
