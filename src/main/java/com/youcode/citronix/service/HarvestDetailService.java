package com.youcode.citronix.service;

import com.youcode.citronix.vm.HarvestDetailVM;

import java.util.List;

public interface HarvestDetailService {

    HarvestDetailVM createHarvestDetail(HarvestDetailVM harvestDetailVM);

    List<HarvestDetailVM> getAllHarvestDetails();

    HarvestDetailVM getHarvestDetailById(Long id);

    void deleteHarvestDetail(Long id);

    List<HarvestDetailVM> getHarvestDetailsByHarvestId(Long harvestId);
    void deleteHarvestDetailsByHarvestId(Long harvestId);
}
