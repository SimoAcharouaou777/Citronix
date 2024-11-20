package com.youcode.citronix.controller;

import com.youcode.citronix.dto.FieldDTO;
import com.youcode.citronix.service.FieldServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/fields")
public class FieldController {

    @Autowired
    private  FieldServiceImpl fieldServiceImpl;

    @PostMapping("/{id}")
    public ResponseEntity<FieldDTO> createField(@PathVariable Long id ,@RequestBody FieldDTO fieldDTO){
        FieldDTO createdField = fieldServiceImpl.createField(id,fieldDTO);
        return new ResponseEntity<>(createdField, HttpStatus.CREATED);
    }
    @GetMapping
    public ResponseEntity<List<FieldDTO>> getAllFields(){
        List<FieldDTO> fields = fieldServiceImpl.getAllFields();
        return new ResponseEntity<>(fields, HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<FieldDTO> getFieldById(@PathVariable Long id){
        FieldDTO field = fieldServiceImpl.getFieldById(id);
        return new ResponseEntity<>(field, HttpStatus.OK);
    }
    @PutMapping("/{id}")
    public ResponseEntity<FieldDTO> updateField(@PathVariable Long id, @RequestBody FieldDTO fieldDTO){
        FieldDTO updatedField = fieldServiceImpl.updateField(id, fieldDTO);
        return new ResponseEntity<>(updatedField, HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteField(@PathVariable Long id){
        fieldServiceImpl.deleteField(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }



}
