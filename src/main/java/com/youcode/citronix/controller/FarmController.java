package com.youcode.citronix.controller;

import com.youcode.citronix.dto.FarmDTO;
import com.youcode.citronix.service.FarmServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/farms")
public class FarmController {

    @Autowired
    private FarmServiceImpl farmServiceImpl;

    @PostMapping
    public ResponseEntity<FarmDTO> createFarm(@RequestBody FarmDTO farmDTO){
        FarmDTO createdFarm = farmServiceImpl.createFarm(farmDTO);
        return new ResponseEntity<>(createdFarm, HttpStatus.CREATED);
    }


    @GetMapping
    public ResponseEntity<List<FarmDTO>> getAllFarms(){
        List<FarmDTO> farms = farmServiceImpl.getAllFarms();
        return new ResponseEntity<>(farms, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FarmDTO> getFarmById(@PathVariable Long id){
        FarmDTO farm = farmServiceImpl.getFarmById(id);
        return new ResponseEntity<>(farm, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<FarmDTO> updateFarm(@PathVariable Long id, @RequestBody FarmDTO farmDTO){
        FarmDTO updatedFarm = farmServiceImpl.updateFarm(id, farmDTO);
        return new ResponseEntity<>(updatedFarm, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFarm(@PathVariable Long id){
        farmServiceImpl.deleteFarm(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/search")
    public ResponseEntity<List<FarmDTO>> searchFarms(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String location,
            @RequestParam(required = false) Double minSize,
            @RequestParam(required = false) Double maxSize,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)LocalDate creationDate
            ){
        List<FarmDTO> farms = farmServiceImpl.searchFarms(name, location, minSize, maxSize, creationDate);
        return new ResponseEntity<>(farms, HttpStatus.OK);
    }
}
