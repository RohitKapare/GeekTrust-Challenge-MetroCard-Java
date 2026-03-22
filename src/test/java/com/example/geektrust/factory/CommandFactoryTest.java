package com.example.geektrust.factory;

import static org.junit.jupiter.api.Assertions.assertTrue;

import com.example.geektrust.command.BalanceCommand;
import com.example.geektrust.command.CheckInCommand;
import com.example.geektrust.command.PrintSummaryCommand;
import com.example.geektrust.service.JourneyService;
import com.example.geektrust.service.MetroCardService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class CommandFactoryTest {

  @Mock
  private MetroCardService metroCardService;
  @Mock
  private JourneyService journeyService;

  private CommandFactory commandFactory;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.initMocks(this);
    commandFactory = new CommandFactory(metroCardService, journeyService);
  }

  @Test
  @DisplayName("Should return respective command for given command")
  public void shouldReturnRespectiveCommand() {
    assertTrue(commandFactory.getCommand("BALANCE") instanceof BalanceCommand);
    assertTrue(commandFactory.getCommand("CHECK_IN") instanceof CheckInCommand);
    assertTrue(commandFactory.getCommand("PRINT_SUMMARY") instanceof PrintSummaryCommand);
  }
}
