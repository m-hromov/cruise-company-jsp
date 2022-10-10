package com.cruisecompany.service;

import com.cruisecompany.db.dto.CruiseShowDTO;
import com.cruisecompany.entity.Cruise;
import com.cruisecompany.entity.Order;
import com.cruisecompany.exception.ServiceException;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface CruiseService {
    List<CruiseShowDTO> getAllCruiseShowDTO() throws ServiceException;
    List<CruiseShowDTO> getAllFilteredCruiseShowDTO(LocalDate dateFrom, LocalDate dateTo, int durationFrom, int durationTo,
                              int limit, int offset) throws ServiceException;
    int getPageAmount(LocalDate dateFrom, LocalDate dateTo, int durationFrom, int durationTo, int limit) throws ServiceException;
    List<CruiseShowDTO> getAllCruiseShowDTOBySearch(String str);
    void addCruise(Cruise cruise) throws ServiceException;
    Optional<CruiseShowDTO> getCruiseShowDTOById(long id) throws ServiceException;
}
