package com.example.geektrust.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class MetroCardTest {

  private MetroCard card;

  @BeforeEach
  void setUp() {
    card = new MetroCard("MC1", 600);
  }

  @Test
  @DisplayName("Should return correct card number")
  public void shouldReturnCorrectCardNumber() {
    assertEquals("MC1", card.getCardNumber());
  }

  @Test
  @DisplayName("Should return correct initial balance")
  public void shouldReturnCorrectInitialBalance() {
    assertEquals(600, card.getBalance());
  }

  @Test
  @DisplayName("Should deduct correct amount from balance")
  public void deductCorrectAmount() {
    card.deduct(200);
    assertEquals(400, card.getBalance());
  }

  @Test
  @DisplayName("Should recharge with correct amount to balance")
  public void fun() {
    card.recharge(200);
    assertEquals(800, card.getBalance());
  }
}
