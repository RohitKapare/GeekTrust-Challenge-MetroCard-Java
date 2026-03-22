package com.example.geektrust.repository;

import com.example.geektrust.model.Station;

import java.util.*;

public class StationRepository {

  private static StationRepository instance;

  private final Map<String, Station> stationStorage = new LinkedHashMap<>();

  private StationRepository() {
    stationStorage.put("CENTRAL", new Station("CENTRAL"));
    stationStorage.put("AIRPORT", new Station("AIRPORT"));
  }

  public static StationRepository getInstance() {
    if (instance == null) {
      instance = new StationRepository();
    }
    return instance;
  }


  public Optional<Station> findByName(String name) {
    return Optional.ofNullable(stationStorage.get(name.toUpperCase()));
  }


  public List<Station> findAll() {
    return new ArrayList<>(stationStorage.values());
  }

}
