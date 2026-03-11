package com.example.geektrust.model;

public class Station {

  private final String stationName;
  private double totalCollection;
  private double totalDiscount;

  public Station(String stationName, double totalDiscount, double totalCollection) {
    this.stationName = stationName;
    this.totalDiscount = totalDiscount;
    this.totalCollection = totalCollection;
  }

  public String getStationName() {
    return stationName;
  }

  public double getTotalCollection() {
    return totalCollection;
  }

  public void setTotalCollection(double totalCollection) {
    this.totalCollection = totalCollection;
  }

  public double getTotalDiscount() {
    return totalDiscount;
  }

  public void setTotalDiscount(double totalDiscount) {
    this.totalDiscount = totalDiscount;
  }
}
