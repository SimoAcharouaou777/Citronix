package com.youcode.citronix.mapper;

import com.youcode.citronix.entity.HarvestDetail;
import com.youcode.citronix.vm.HarvestDetailVM;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface HarvestDetailMapper {

    HarvestDetailMapper INSTANCE = Mappers.getMapper(HarvestDetailMapper.class);

    HarvestDetail harvestDetailVMToHarvestDetail(HarvestDetailVM harvestDetailVM);

    HarvestDetailVM harvestDetailToHarvestDetailVM(HarvestDetail harvestDetail);
}
