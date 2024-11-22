package com.youcode.citronix.service;

import com.youcode.citronix.dto.FieldDTO;
import com.youcode.citronix.entity.Farm;
import com.youcode.citronix.entity.Field;
import com.youcode.citronix.mapper.FieldMapper;
import com.youcode.citronix.repository.FarmRepository;
import com.youcode.citronix.repository.FieldRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class FieldServiceImpl implements FieldService {

    @Autowired
    private FieldRepository fieldRepository;
    @Autowired
    private FarmRepository farmRepository;

    private final FieldMapper fieldMapper = FieldMapper.INSTANCE;

    @Override
    public FieldDTO createField(Long id , FieldDTO fieldDTO) {
        Farm farm = farmRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Farm not found"));

        long fieldCount = fieldRepository.countByFarmId(id);
        if(fieldCount >= 10){
            throw new RuntimeException("A farm can have a maximum of 10 fields");
        }
        double totalFieldSize = fieldRepository.findByFarmId(id).stream()
                .mapToDouble(Field::getSize)
                .sum();

        if(totalFieldSize + fieldDTO.getSize() > farm.getSize()){
            throw new RuntimeException("Field size exceeds the available farm size");
        }

        if(fieldDTO.getSize() > farm.getSize() * 0.5){
            throw new RuntimeException("Field size cannot exceed 50% of the farm size");
        }

        Field field = fieldMapper.fieldDTOToField(fieldDTO);
        field.setFarm(farm);

        Field savedField = fieldRepository.save(field);
        return fieldMapper.fieldToFieldDTO(savedField);
    }

    @Override
    public List<FieldDTO> getAllFields() {
         List<Field> fields = fieldRepository.findAll();
         return fieldMapper.fieldsToFieldDTOs(fields);
    }


    @Override
    public FieldDTO getFieldById(Long id) {
        Field field = fieldRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Field not found"));
        return fieldMapper.fieldToFieldDTO(field);
    }

    @Override
    public FieldDTO updateField(Long fieldId, Long farmId, FieldDTO fieldDTO) {
        Field field = fieldRepository.findById(fieldId)
                .orElseThrow(() -> new RuntimeException("Field not found"));
        Farm farm = farmRepository.findById(farmId)
                .orElseThrow(() -> new RuntimeException("Farm not found"));

        long fieldCount = fieldRepository.countByFarmId(farmId);
        if(fieldCount >= 10){
            throw new RuntimeException("A farm can have a maximum of 10 fields");
        }

        double existingFieldSize = fieldRepository.findByFarmId(farmId).stream()
                        .filter(f -> !f.getId().equals(fieldId))
                                .mapToDouble(Field::getSize)
                                        .sum();
        if(existingFieldSize + fieldDTO.getSize() > farm.getSize()){
            throw new RuntimeException("Field size exceeds the available farm size");
        }

        if(fieldDTO.getSize() > farm.getSize() * 0.5){
            throw new RuntimeException("Field size cannot exceed 50% of the farm size");
        }

        field.setFarm(farm);
        field.setSize(fieldDTO.getSize());

        Field updatedField = fieldRepository.save(field);
        return fieldMapper.fieldToFieldDTO(updatedField);
    }

    @Override
    public void deleteField(Long id) {
        Field field = fieldRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Field not found"));
        fieldRepository.delete(field);
    }
}
