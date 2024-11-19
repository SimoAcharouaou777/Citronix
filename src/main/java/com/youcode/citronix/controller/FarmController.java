package com.youcode.citronix.controller;

import com.youcode.citronix.dto.FarmDTO;
import com.youcode.citronix.entity.Farm;
import com.youcode.citronix.service.FarmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/farms")
public class FarmController {

    @Autowired
    private FarmService farmService;

    @PostMapping
    public ResponseEntity<FarmDTO> createFarm(@RequestBody FarmDTO farmDTO){
        FarmDTO createdFarm = farmService.createFarm(farmDTO);
        return new ResponseEntity<>(createdFarm, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<FarmDTO>> getAllFarms(){
        List<FarmDTO> farms = farmService.getAllFarms();
        return new ResponseEntity<>(farms, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FarmDTO> getFarmById(@PathVariable Long id){
        FarmDTO farm = farmService.getFarmById(id);
        return new ResponseEntity<>(farm, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<FarmDTO> updateFarm(@PathVariable Long id, @RequestBody FarmDTO farmDTO){
        FarmDTO updatedFarm = farmService.updateFarm(id, farmDTO);
        return new ResponseEntity<>(updatedFarm, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFarm(@PathVariable Long id){
        farmService.deleteFarm(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
