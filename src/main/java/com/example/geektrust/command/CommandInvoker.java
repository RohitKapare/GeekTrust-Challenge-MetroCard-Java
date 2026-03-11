package com.example.geektrust.command;

import java.util.HashMap;
import java.util.Map;

public class CommandInvoker {
  private final Map<String, ICommand> commandMap = new HashMap<>();

  public void register(String commandName, ICommand command) {
    commandMap.put(commandName, command);
  }

  public void invoke(String input) {
    String[] tokens = input.split(" ");
    String commandName = tokens[0];

    if(commandMap.containsKey(commandName)) {
      commandMap.get(commandName).execute(tokens);
    }
  }

}
