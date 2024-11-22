package com.youcode.citronix.mapper;

import com.youcode.citronix.entity.Harvest;
import com.youcode.citronix.vm.HarvestVM;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface HarvestMapper {

    HarvestMapper INSTANCE = Mappers.getMapper(HarvestMapper.class);

    Harvest harvestVMToHarvest(HarvestVM harvestVM);

    HarvestVM harvestToHarvestVM(Harvest harvest);
}
