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
    public FieldDTO updateField(Long id, FieldDTO fieldDTO) {
        Field field = fieldRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Field not found"));
        Farm farm = farmRepository.findById(fieldDTO.getFarmId())
                .orElseThrow(() -> new RuntimeException("Farm not found"));
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
