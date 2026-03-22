package com.example.geektrust.command;

import static org.junit.jupiter.api.Assertions.assertThrows;

import com.example.geektrust.service.MetroCardService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class BalanceCommandTest {
  @Mock
  private MetroCardService metroCardService;

  private BalanceCommand balanceCommand;

  @BeforeEach
  void setUp() {
      MockitoAnnotations.initMocks(this);
      balanceCommand = new BalanceCommand(metroCardService);
  }

  @Test
  @DisplayName("Should throw IllegalArgumentException when invalid arguments are passed")
  void InvalidCommandThrowIllegalArgumentExceptionForWrongTokenCount() {
      String[] tokens = {"BALANCE"};   // missing card number and amount

      assertThrows(IllegalArgumentException.class,
          () -> balanceCommand.execute(tokens));
  }
}
