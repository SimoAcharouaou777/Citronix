package com.youcode.citronix.mapper;

import com.youcode.citronix.dto.FarmDTO;
import com.youcode.citronix.entity.Farm;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface FarmMapper {

    FarmMapper INSTANCE = Mappers.getMapper(FarmMapper.class);

    FarmDTO farmToFarmDTO(Farm farm);
    Farm farmDTOToFarm(FarmDTO farmDTO);

    List<FarmDTO> farmsToFarmDTOs(List<Farm> farms);
    List<Farm> farmDTOsToFarms(List<FarmDTO> farmDTOs);
}
