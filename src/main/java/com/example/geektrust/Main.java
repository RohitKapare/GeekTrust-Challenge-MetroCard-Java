package com.example.geektrust;

import com.example.geektrust.command.CommandInvoker;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;

public class Main {

  // Initilize repositories
  // Initilize services
  // Initilize commands
  // Initilize command invoker
  private final CommandInvoker commandInvoker = new CommandInvoker();
  // Register commands

  public static void main(String[] args) {

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
