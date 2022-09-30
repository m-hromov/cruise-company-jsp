package com.cruisecompany.db.dao;

import com.cruisecompany.entity.Station;

import java.util.List;

public interface StationDAO extends DAO<Station>{
    List<Station> getAllStationsByCruiseID(long id);
}
