package com.youcode.citronix.mapper;

import com.youcode.citronix.dto.FieldDTO;
import com.youcode.citronix.entity.Field;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface FieldMapper {
    FieldMapper INSTANCE = Mappers.getMapper(FieldMapper.class);

    @Mappings({
            @Mapping(source = "farm.id", target = "farmId")
    })
    FieldDTO fieldToFieldDTO(Field field);

    @Mappings({
            @Mapping(source = "farmId", target = "farm.id")
    })
    Field fieldDTOToField(FieldDTO fieldDTO);

    List<FieldDTO> fieldsToFieldDTOs(List<Field> fields);
    List<Field> fieldDTOsToFields(List<FieldDTO> fieldDTOs);
}
