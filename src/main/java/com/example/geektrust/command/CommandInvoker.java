package com.example.geektrust.command;

import com.example.geektrust.factory.CommandFactory;

public class CommandInvoker {
  private final CommandFactory commandFactory;

  public CommandInvoker(CommandFactory commandFactory) {
    this.commandFactory = commandFactory;
  }

  public void invoke(String line) {
    if(line == null || line.trim().isEmpty()) return;
    String[] tokens = line.trim().split("\\s+");
    Command command = commandFactory.getCommand(tokens[0]);
    command.execute(tokens);
  }
}
