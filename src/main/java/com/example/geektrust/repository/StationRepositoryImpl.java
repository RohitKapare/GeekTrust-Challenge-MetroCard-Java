package com.example.geektrust.repository;

import com.example.geektrust.model.Station;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StationRepositoryImpl implements StationRepository {

  private static StationRepository instance;

  private final Map<String, Station> stationStorage = new HashMap<>();

  private StationRepositoryImpl() {
    stationStorage.put("CENTRAL", new Station("CENTRAL"));
    stationStorage.put("AIRPORT", new Station("AIRPORT"));
  }

  public static StationRepository getInstance() {
    if (instance == null) {
      instance = new StationRepositoryImpl();
    }
    return instance;
  }

  @Override
  public Station findByName(String name) {
    return stationStorage.get(name.toUpperCase());
  }

  @Override
  public List<Station> findAll() {
    return new ArrayList<>(stationStorage.values());
  }

}
