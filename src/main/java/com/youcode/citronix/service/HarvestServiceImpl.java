package com.youcode.citronix.service;

import com.youcode.citronix.entity.*;
import com.youcode.citronix.mapper.HarvestDetailMapper;
import com.youcode.citronix.mapper.HarvestMapper;
import com.youcode.citronix.repository.FieldRepository;
import com.youcode.citronix.repository.HarvestDetailRepository;
import com.youcode.citronix.repository.HarvestRepository;
import com.youcode.citronix.repository.TreeRepository;
import com.youcode.citronix.vm.HarvestVM;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Month;
import java.time.Period;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class HarvestServiceImpl implements HarvestService{

    @Autowired
    private HarvestRepository harvestRepository;

    @Autowired
    private FieldRepository fieldRepository;

    @Autowired
    private TreeRepository treeRepository;

    @Autowired
    private HarvestDetailRepository harvestDetailRepository;


    private final HarvestMapper harvestMapper = HarvestMapper.INSTANCE;
    private final HarvestDetailMapper harvestDetailMapper = HarvestDetailMapper.INSTANCE;


    @Override
    public HarvestVM createHarvest(HarvestVM harvestVM) {
        Field field = fieldRepository.findById(harvestVM.getFieldId())
                .orElseThrow(() -> new RuntimeException("Field not found"));

        LocalDate harvestDate = harvestVM.getHarvestDate();
        Season season = determineSeason(harvestDate);

        if(harvestRepository.existsByField_IdAndSeasonAndHarvestDateYear(field.getId(), season,harvestDate.getYear())){
            throw new RuntimeException("Harvest already exists for this field and season in the same year");
        }

        List<Tree> trees = treeRepository.findByField_Id(field.getId());
        double totalQuantity = trees.stream().mapToDouble(this::calculateTreeProductivity).sum();

        Harvest harvest = new Harvest();
        harvest.setField(field);
        harvest.setSeason(season);
        harvest.setHarvestDate(harvestDate);
        harvest.setTotalQuantity(totalQuantity);
        Harvest savedHarvest = harvestRepository.save(harvest);

        for(Tree tree : trees){
            HarvestDetail harvestDetail = new HarvestDetail();
            harvestDetail.setHarvest(savedHarvest);
            harvestDetail.setTree(tree);
            harvestDetail.setQuantityHarvested(calculateTreeProductivity(tree));
            harvestDetailRepository.save(harvestDetail);
        }

        return harvestMapper.harvestToHarvestVM(savedHarvest);
    }

    private  double calculateTreeProductivity(Tree tree){
        int treeAge = Period.between(tree.getPlantingDate(), LocalDate.now()).getYears();
        if(treeAge < 3){
            return 2.5;
        } else if (treeAge <= 10) {
            return 12.0;
        } else if (treeAge <= 20) {
            return 20.0;
        } else {
            return 0.0;

        }
    }
    private Season determineSeason(LocalDate date){
        Month month = date.getMonth();
        if(month == Month.DECEMBER || month == Month.JANUARY || month == Month.FEBRUARY){
            return Season.WINTER;
        } else if (month == Month.MARCH || month == Month.APRIL || month == Month.MAY){
            return Season.SPRING;
        } else if (month == Month.JUNE || month == Month.JULY || month == Month.AUGUST){
            return Season.SUMMER;
        } else {
            return Season.AUTUMN;
        }
    }

    @Override
    public List<HarvestVM> getAllHarvests() {
        List<Harvest> harvests = harvestRepository.findAll();
        return harvests.stream().map(harvestMapper::harvestToHarvestVM).collect(Collectors.toList());
    }

    @Override
    public HarvestVM getHarvestById(Long id) {
        Harvest harvest = harvestRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("harvest not found"));
        return harvestMapper.harvestToHarvestVM(harvest);
    }

    @Override
    public HarvestVM updateHarvest(Long id, HarvestVM harvestVM) {
        Harvest existingHarvest = harvestRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("harvest not found"));

        LocalDate harvestDate = harvestVM.getHarvestDate();
        Season season = determineSeason(harvestDate);

        if(!existingHarvest.getSeason().equals(harvestVM.getSeason()) &&
                harvestRepository.existsByField_IdAndSeasonAndHarvestDateYear(existingHarvest.getField().getId(), season,harvestDate.getYear() )){
            throw new RuntimeException("Harvest already exists for this field and season in the same year");
        }
        existingHarvest.setHarvestDate(harvestDate);
        existingHarvest.setSeason(season);

        List<Tree> trees = treeRepository.findByField_Id(existingHarvest.getField().getId());
        double totalQuantity = trees.stream().mapToDouble(this::calculateTreeProductivity).sum();
        existingHarvest.setTotalQuantity(totalQuantity);

        Harvest updatedHarvest = harvestRepository.save(existingHarvest);
        harvestDetailRepository.deleteAll(existingHarvest.getHarvestDetails());
        for (Tree tree : trees){
            HarvestDetail harvestDetail = new HarvestDetail();
            harvestDetail.setHarvest(updatedHarvest);
            harvestDetail.setTree(tree);
            harvestDetail.setQuantityHarvested(calculateTreeProductivity(tree));
            harvestDetailRepository.save(harvestDetail);
        }
        return harvestMapper.harvestToHarvestVM(updatedHarvest);
    }

    @Override
    public void deleteHarvest(Long id) {
        Harvest harvest = harvestRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("harvest not found"));
        harvestRepository.delete(harvest);
    }
}