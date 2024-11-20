package com.youcode.citronix.service;

import com.youcode.citronix.dto.FieldDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface FieldService {

    FieldDTO createField(Long id ,FieldDTO fieldDTO);

    List<FieldDTO> getAllFields();

    FieldDTO getFieldById(Long id);

    FieldDTO updateField(Long id, FieldDTO fieldDTO);

    void deleteField(Long id);
}
