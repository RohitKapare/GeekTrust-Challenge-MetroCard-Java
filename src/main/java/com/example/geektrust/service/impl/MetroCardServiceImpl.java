package com.example.geektrust.service.impl;

import com.example.geektrust.exceptions.ResourceNotFoundException;
import com.example.geektrust.model.MetroCard;
import com.example.geektrust.repository.MetroCardRepository;
import com.example.geektrust.service.MetroCardService;

public class MetroCardServiceImpl implements MetroCardService {

  private final MetroCardRepository metroCardRepository;

  public MetroCardServiceImpl(MetroCardRepository metroCardRepository) {
    this.metroCardRepository = metroCardRepository;
  }

  @Override
  public void setBalance(String cardNumber, int balance) {
    MetroCard card = new MetroCard(cardNumber, balance);
    metroCardRepository.save(card);
  }

  @Override
  public MetroCard getCard(String cardNumber) {
    return metroCardRepository.findById(cardNumber).orElseThrow(
        () -> new ResourceNotFoundException("Metro card not found for card: " + cardNumber));
  }

  @Override
  public int rechargeIfRequired(MetroCard card, int requiredFare) {
    int neededRecharge = requiredFare - card.getBalance();
    if (neededRecharge <= 0) {
      return 0;
    }
    card.recharge(neededRecharge);
    return (int) Math.ceil(neededRecharge * 0.02);
  }
}
