package com.cruisecompany.service;

import com.cruisecompany.db.dto.CruiseShowDTO;

import java.util.List;

public interface CruiseService {
    List<CruiseShowDTO> findAll();
    List<CruiseShowDTO> getAllCruiseShowDTO();
    List<CruiseShowDTO> getAllPassengerCruiseShowDTO(long id);
    List<CruiseShowDTO> getAllCruiseShowDTOBySearch(String str);
}
