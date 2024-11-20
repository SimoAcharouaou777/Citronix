package com.youcode.citronix.service;

import com.youcode.citronix.dto.FarmDTO;
import com.youcode.citronix.entity.Farm;
import com.youcode.citronix.mapper.FarmMapper;
import com.youcode.citronix.repository.FarmRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class FarmService {

    @Autowired
    private FarmRepository farmRepository;

    private final FarmMapper farmMapper = FarmMapper.INSTANCE;

    public FarmDTO createFarm(FarmDTO farmDTO) {
        Farm farm = farmMapper.farmDTOToFarm(farmDTO);
        Farm savedFarm = farmRepository.save(farm);
        return farmMapper.farmToFarmDTO(savedFarm);
    }

    public List<FarmDTO> getAllFarms() {
        List<Farm> farms = farmRepository.findAll();
        return farmMapper.farmsToFarmDTOs(farms);
    }

    public FarmDTO getFarmById(Long id) {
        Farm farm = farmRepository.findById(id).orElseThrow(() -> new RuntimeException("Farm not found"));
        return farmMapper.farmToFarmDTO(farm);
    }

    public FarmDTO updateFarm(Long id, FarmDTO farmDTO) {
        Farm existingFarm = farmRepository.findById(id).orElseThrow(() -> new RuntimeException("Farm not found"));
        existingFarm.setName(farmDTO.getName());
        existingFarm.setLocation(farmDTO.getLocation());
        existingFarm.setSize(farmDTO.getSize());
        existingFarm.setCreationDate(farmDTO.getCreationDate());

        Farm updatedFarm = farmRepository.save(existingFarm);
        return farmMapper.farmToFarmDTO(updatedFarm);
    }

    public void deleteFarm(Long id) {
        farmRepository.deleteById(id);
    }
}
