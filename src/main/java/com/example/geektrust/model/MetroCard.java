package com.example.geektrust.model;

public class MetroCard {

  private final String cardNumber;
  private int balance;

  public MetroCard(String cardNumber, int balance) {
    this.cardNumber = cardNumber;
    this.balance = balance;
  }

  public int getBalance() {
    return balance;
  }

  public void setBalance(int balance) {
    this.balance = balance;
  }

  public String getCardNumber() {
    return cardNumber;
  }

  public void deduct(int amount) {
    this.balance -= amount;
  }

  public void recharge(int amount) {
    this.balance += amount;
  }

}
