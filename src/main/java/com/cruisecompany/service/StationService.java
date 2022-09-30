package com.cruisecompany.service;

import com.cruisecompany.entity.Station;

import java.util.List;

public interface StationService {
    List<Station> getAll();
    void addStation(Station station);
}
