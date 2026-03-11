package com.example.geektrust.repository;

import com.example.geektrust.model.Station;
import java.util.List;

public interface StationRepository {

  Station findByName(String name);

  List<Station> findAll();
}
