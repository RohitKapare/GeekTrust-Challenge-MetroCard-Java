package com.example.geektrust.command;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;

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
  @DisplayName("Should call metroCardService.setBalance method")
  void shouldCallMetrocardServiceSetBalanceMethod() {
    String[] tokens = {"BALANCE", "MC1", "600"};

    balanceCommand.execute(tokens);

    verify(metroCardService).setBalance("MC1", 600);
  }

  @Test
  @DisplayName("Should throw IllegalArgumentException when invalid arguments are passed")
  void InvalidCommandThrowIllegalArgumentExceptionForWrongTokenCount() {
    String[] tokens = {"BALANCE"};   // missing card number and amount

    assertThrows(
        IllegalArgumentException.class,
        () -> balanceCommand.execute(tokens)
    );
  }
}
