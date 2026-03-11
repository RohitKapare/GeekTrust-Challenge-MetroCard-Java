package com.example.geektrust.repository;

import com.example.geektrust.model.MetroCard;
import java.util.HashMap;
import java.util.Map;

public class MetroCardRepositoryImpl implements MetroCardRepository {
  private static MetroCardRepositoryImpl instance;
  private final Map<String, MetroCard> cardsStorage = new HashMap<>();

  private MetroCardRepositoryImpl() {}

  public static MetroCardRepositoryImpl getInstance() {
    if (instance == null) {
      instance = new MetroCardRepositoryImpl();
    }
    return instance;
  }
  @Override
  public void save(MetroCard metroCard) {
    cardsStorage.put(metroCard.getCardNumber(), metroCard);
  }

  @Override
  public MetroCard findById(String cardNumber) {
    return cardsStorage.get(cardNumber);
  }

  @Override
  public boolean isExists(String cardNumber) {
    return cardsStorage.containsKey(cardNumber);
  }
}
