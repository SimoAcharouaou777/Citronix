package com.youcode.citronix.mapper;

import com.youcode.citronix.entity.Harvest;
import com.youcode.citronix.vm.HarvestVM;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface HarvestMapper {

    HarvestMapper INSTANCE = Mappers.getMapper(HarvestMapper.class);

    HarvestVM harvestToHarvestVM(Harvest harvest);

    Harvest harvestVMToHarvest(HarvestVM harvestVM);
}
