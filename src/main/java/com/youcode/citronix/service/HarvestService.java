package com.youcode.citronix.service;

import com.youcode.citronix.entity.Harvest;
import com.youcode.citronix.vm.HarvestVM;

import java.util.List;

public interface HarvestService {

    HarvestVM createHarvest(HarvestVM harvestVM);

    List<HarvestVM> getAllHarvests();

    HarvestVM getHarvestById(Long id);

    HarvestVM updateHarvest(Long id , HarvestVM harvestVM);

    void deleteHarvest(Long id);

    List<HarvestVM> getHarvestsByFieldId(Long fieldId);
}
