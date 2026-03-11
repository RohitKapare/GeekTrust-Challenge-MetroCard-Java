package com.example.geektrust.model;

public class MetroCard {

  private final String cardNumber;
  private double balance;

  public MetroCard(String cardNumber, double balance) {
    this.cardNumber = cardNumber;
    this.balance = balance;
  }

  public double getBalance() {
    return balance;
  }

  public void setBalance(double balance) {
    this.balance = balance;
  }

  public String getCardNumber() {
    return cardNumber;
  }

  public void deduct(double amount) {
    this.balance -= amount;
  }

  public void recharge(double amount) {
    this.balance += amount;
  }

}
