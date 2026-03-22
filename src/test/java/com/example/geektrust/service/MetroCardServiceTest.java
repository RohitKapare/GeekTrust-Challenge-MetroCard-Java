package com.example.geektrust.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import com.example.geektrust.exceptions.ResourceNotFoundException;
import com.example.geektrust.model.MetroCard;
import com.example.geektrust.repository.MetroCardRepository;
import com.example.geektrust.service.impl.MetroCardServiceImpl;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class MetroCardServiceTest {

  @Mock
  private MetroCardRepository metroCardRepository;

  private MetroCardService metroCardService;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.initMocks(this);
    metroCardService = new MetroCardServiceImpl(metroCardRepository);
  }

  @Test
  @DisplayName("Should recharge needed amount and return service fee")
  public void returnNeededAmountAndServiceFee() {
    MetroCard card = new MetroCard("MC1", 50);
    int serviceFee = metroCardService.rechargeIfRequired(card, 200);

    assertEquals(3, serviceFee);
    assertEquals(200, card.getBalance());
  }

  @Test
  @DisplayName("Should return zero service fee when balance is sufficient")
  public void noRechargeNeededWhenSufficientBalance() {
    MetroCard card = new MetroCard("MC1", 500);
    int serviceFee = metroCardService.rechargeIfRequired(card, 200);

    assertEquals(0, serviceFee);
    assertEquals(500, card.getBalance());
  }

  @Test
  @DisplayName("Should recharge when balance is exactly zero")
  public void rechargeAtZeroBalance() {
    MetroCard card = new MetroCard("MC1", 0);
    int serviceFee = metroCardService.rechargeIfRequired(card, 50);

    assertEquals(1, serviceFee);
    assertEquals(50, card.getBalance());
  }

  @Test
  @DisplayName("Service fee should round up and now down")
  public void serviceFeeRoundUp() {
    MetroCard card = new MetroCard("MC1", 0);
    int serviceFee = metroCardService.rechargeIfRequired(card, 60);

    assertEquals(2, serviceFee);
  }

  @Test
  @DisplayName("Should throw ResourceNotFoundException when card not in repository")
  void InvalidCardThrowResourceNotFoundException() {
    when(metroCardRepository.findById("INVALID_CARD")).thenReturn(Optional.empty());

    assertThrows(ResourceNotFoundException.class, () -> metroCardService.getCard("INVALID_CARD"));
  }
}
