package com.youcode.citronix.service;

import com.youcode.citronix.dto.FarmDTO;

import java.time.LocalDate;
import java.util.List;

public interface FarmService {

    FarmDTO createFarm(FarmDTO farmDTO);

    List<FarmDTO> getAllFarms();

    FarmDTO getFarmById(Long id);

    FarmDTO updateFarm(Long id, FarmDTO farmDTO);

    void deleteFarm(Long id);

    List<FarmDTO> searchFarms(String name, String location, Double minSize, Double maxSize, LocalDate creationDate);
}
