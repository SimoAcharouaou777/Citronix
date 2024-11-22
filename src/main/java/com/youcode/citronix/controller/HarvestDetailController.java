package com.youcode.citronix.controller;

import com.youcode.citronix.service.HarvestDetailService;
import com.youcode.citronix.service.HarvestDetailServiceImpl;
import com.youcode.citronix.vm.HarvestDetailVM;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/harvest-details")
public class HarvestDetailController {

    @Autowired
    private HarvestDetailServiceImpl harvestDetailServiceImp;

    @PostMapping
    public ResponseEntity<HarvestDetailVM> createHarvestDetail(@RequestBody HarvestDetailVM harvestDetailVM){
        HarvestDetailVM createdDetail = harvestDetailServiceImp.createHarvestDetail(harvestDetailVM);
        return new ResponseEntity<>(createdDetail, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<HarvestDetailVM>> getAllHarvestDetails(){
        List<HarvestDetailVM> details = harvestDetailServiceImp.getAllHarvestDetails();
        return new ResponseEntity<>(details, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<HarvestDetailVM> getHarvestDetailById(@PathVariable Long id){
        HarvestDetailVM harvest = harvestDetailServiceImp.getHarvestDetailById(id);
        return new ResponseEntity<>(harvest, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteHarvestDetail(@PathVariable Long id){
        harvestDetailServiceImp.deleteHarvestDetail(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
