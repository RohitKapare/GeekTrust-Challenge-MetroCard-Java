package com.example.geektrust.repository;

import com.example.geektrust.model.MetroCard;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class MetroCardRepository {
  private static MetroCardRepository instance;
  private final Map<String, MetroCard> cardsStorage = new HashMap<>();

  private MetroCardRepository() {}

  public static MetroCardRepository getInstance() {
    if (instance == null) {
      instance = new MetroCardRepository();
    }
    return instance;
  }

  public void save(MetroCard metroCard) {
    cardsStorage.put(metroCard.getCardNumber(), metroCard);
  }


  public Optional<MetroCard> findById(String cardNumber) {
    return Optional.ofNullable(cardsStorage.get(cardNumber));
  }


  public boolean isExists(String cardNumber) {
    return cardsStorage.containsKey(cardNumber);
  }
}
