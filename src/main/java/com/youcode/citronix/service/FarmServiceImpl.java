package com.youcode.citronix.service;

import com.youcode.citronix.dto.FarmDTO;
import com.youcode.citronix.entity.Farm;
import com.youcode.citronix.mapper.FarmMapper;
import com.youcode.citronix.repository.FarmRepository;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class FarmServiceImpl implements FarmService {

    @Autowired
    private FarmRepository farmRepository;

    private final FarmMapper farmMapper = FarmMapper.INSTANCE;

    @Override
    public FarmDTO createFarm(FarmDTO farmDTO) {
        Farm farm = farmMapper.farmDTOToFarm(farmDTO);
        Farm savedFarm = farmRepository.save(farm);
        return farmMapper.farmToFarmDTO(savedFarm);
    }

    @Override
    public List<FarmDTO> getAllFarms() {
        List<Farm> farms = farmRepository.findAll();
        return farmMapper.farmsToFarmDTOs(farms);
    }

    @Override
    public FarmDTO getFarmById(Long id) {
        Farm farm = farmRepository.findById(id).orElseThrow(() -> new RuntimeException("Farm not found"));
        return farmMapper.farmToFarmDTO(farm);
    }

    @Override
    public FarmDTO updateFarm(Long id, FarmDTO farmDTO) {
        Farm existingFarm = farmRepository.findById(id).orElseThrow(() -> new RuntimeException("Farm not found"));
        existingFarm.setName(farmDTO.getName());
        existingFarm.setLocation(farmDTO.getLocation());
        existingFarm.setSize(farmDTO.getSize());
        existingFarm.setCreationDate(farmDTO.getCreationDate());

        Farm updatedFarm = farmRepository.save(existingFarm);
        return farmMapper.farmToFarmDTO(updatedFarm);
    }

    @Override
    public void deleteFarm(Long id) {
        farmRepository.deleteById(id);
    }

    @Override
    public List<FarmDTO> searchFarms(String name, String location, Double minSize, Double maxSize, LocalDate creationDate) {
        List<Farm> farms = farmRepository.searchFarmsWithCriteria(name, location, minSize, maxSize, creationDate);
        return farmMapper.farmsToFarmDTOs(farms);
    }


}
