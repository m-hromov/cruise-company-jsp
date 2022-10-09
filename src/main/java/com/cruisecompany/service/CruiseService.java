package com.cruisecompany.service;

import com.cruisecompany.db.dto.CruiseShowDTO;
import com.cruisecompany.entity.Cruise;
import com.cruisecompany.entity.Order;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface CruiseService {
    List<CruiseShowDTO> getAllCruiseShowDTO();
    List<CruiseShowDTO> getAllFilteredCruiseShowDTO(LocalDate dateFrom, LocalDate dateTo, int durationFrom, int durationTo,
                              int limit, int offset);
    int getPageAmount(LocalDate dateFrom, LocalDate dateTo, int durationFrom, int durationTo, int limit);
    List<CruiseShowDTO> getAllCruiseShowDTOBySearch(String str);
    void addCruise(Cruise cruise);
    Optional<CruiseShowDTO> getCruiseShowDTOById(long id);
}
