package com.example.geektrust;

import com.example.geektrust.command.CommandInvoker;
import com.example.geektrust.factory.CommandFactory;
import com.example.geektrust.repository.MetroCardRepository;
import com.example.geektrust.repository.StationRepository;
import com.example.geektrust.service.JourneyService;
import com.example.geektrust.service.JourneyServiceImpl;
import com.example.geektrust.service.MetroCardService;
import com.example.geektrust.service.MetroCardServiceImpl;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;

public class Main {

  public static void main(String[] args) {
    MetroCardRepository metroCardRepository = MetroCardRepository.getInstance();
    StationRepository stationRepository = StationRepository.getInstance();

    MetroCardService metroCardService = new MetroCardServiceImpl(metroCardRepository);
    JourneyService journeyService = new JourneyServiceImpl(metroCardService, stationRepository);

    CommandFactory commandFactory = new CommandFactory(metroCardService, journeyService);
    CommandInvoker commandInvoker = new CommandInvoker(commandFactory);

    try {
      FileInputStream fis = new FileInputStream(args[0]);
      Scanner sc = new Scanner(fis);
      while (sc.hasNextLine()) {
        commandInvoker.invoke(sc.nextLine());
      }
      sc.close();
    } catch (IOException e) {
      e.printStackTrace();
    }

  }
}
