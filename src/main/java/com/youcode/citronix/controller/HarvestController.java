package com.youcode.citronix.controller;

import com.youcode.citronix.service.HarvestService;
import com.youcode.citronix.service.HarvestServiceImpl;
import com.youcode.citronix.vm.HarvestVM;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.List;

@RestController
@RequestMapping("/api/harvest")
public class HarvestController {

    @Autowired
    private HarvestService harvestService;

    @PostMapping
    public ResponseEntity<HarvestVM> createHarvest(@RequestBody HarvestVM harvestVM){
        HarvestVM createdHarvest = harvestService.createHarvest(harvestVM);
        return new ResponseEntity<>(createdHarvest, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<HarvestVM>> getAllHarvests(){
        List<HarvestVM> harvests = harvestService.getAllHarvests();
        return new ResponseEntity<>(harvests, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<HarvestVM> getHarvestById(@PathVariable Long id){
        HarvestVM harvest = harvestService.getHarvestById(id);
        return new ResponseEntity<>(harvest, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<HarvestVM> updateHarvest(@PathVariable Long id, @RequestBody HarvestVM harvestVM){
        HarvestVM updatedHarvest = harvestService.updateHarvest(id,harvestVM);
        return new ResponseEntity<>(updatedHarvest, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteHarvest(@PathVariable Long id){
        harvestService.deleteHarvest(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/field/{fieldId}")
    public ResponseEntity<List<HarvestVM>> getHarvestsByFieldId(@PathVariable Long fieldId){
        List<HarvestVM> harvests = harvestService.getHarvestsByFieldId(fieldId);
        return new ResponseEntity<>(harvests, HttpStatus.OK);
    }

}
