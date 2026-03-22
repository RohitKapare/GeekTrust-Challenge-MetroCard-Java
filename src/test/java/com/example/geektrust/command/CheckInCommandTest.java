package com.example.geektrust.command;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;

import com.example.geektrust.exceptions.InvalidPassengerTypeException;
import com.example.geektrust.model.PassengerType;
import com.example.geektrust.service.JourneyService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class CheckInCommandTest {

  @Mock
  private JourneyService journeyService;

  private CheckInCommand checkInCommand;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.initMocks(this);
    checkInCommand = new CheckInCommand(journeyService);
  }

  @Test
  @DisplayName("Should call journeservice.checkIn method")
  void shouldCallJourneyServiceCheckInMethod() {
    String[] tokens = {"CHECK_IN", "MC1", "ADULT", "CENTRAL"};

    checkInCommand.execute(tokens);

    verify(journeyService).checkIn("MC1", PassengerType.ADULT, "CENTRAL");
  }

  @Test
  @DisplayName("Should throw InvalidPassengerTypeException for invalid passenger type")
  void InvalidPassengerTypeThrowInvalidPassengerTypeException() {
    String[] tokens = {"CHECK_IN", "MC1", "INVALID_TYPE", "CENTRAL"};

    assertThrows(
        InvalidPassengerTypeException.class,
        () -> checkInCommand.execute(tokens)
    );
  }

  @Test
  @DisplayName("Should throw IllegalArgumentException when invalid arguments are passed")
  void InvalidCommandThrowIllegalArgumentExceptionForWrongTokenCount() {
    String[] tokens = {"CHECK_IN", "MC1"};   // missing passenger type and station

    assertThrows(
        IllegalArgumentException.class,
        () -> checkInCommand.execute(tokens)
    );
  }
}
