package com.example.geektrust.repository;

import com.example.geektrust.model.MetroCard;

public interface MetroCardRepository {

  void save(MetroCard metroCard);

  MetroCard findById(String cardNumber);

  boolean isExists(String cardNumber);
}
