package com.cruisecompany.service;

import com.cruisecompany.db.dto.CruiseShowDTO;
import com.cruisecompany.entity.Cruise;

import java.util.List;

public interface CruiseService {
    List<CruiseShowDTO> getAllCruiseShowDTO();
    List<CruiseShowDTO> getAllCruiseShowDTOBySearch(String str);
    void addCruise(Cruise cruise);
}
