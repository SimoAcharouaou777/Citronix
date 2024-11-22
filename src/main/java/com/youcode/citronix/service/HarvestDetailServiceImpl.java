package com.youcode.citronix.service;

import com.youcode.citronix.entity.Harvest;
import com.youcode.citronix.entity.HarvestDetail;
import com.youcode.citronix.entity.Tree;
import com.youcode.citronix.mapper.HarvestDetailMapper;
import com.youcode.citronix.repository.HarvestDetailRepository;
import com.youcode.citronix.repository.HarvestRepository;
import com.youcode.citronix.repository.TreeRepository;
import com.youcode.citronix.vm.HarvestDetailVM;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class HarvestDetailServiceImpl implements HarvestDetailService{

    @Autowired
    private HarvestDetailRepository harvestDetailRepository;

    @Autowired
    private HarvestRepository harvestRepository;

    @Autowired
    private TreeRepository treeRepository;

    private final HarvestDetailMapper harvestDetailMapper = HarvestDetailMapper.INSTANCE;

    @Override
    public HarvestDetailVM createHarvestDetail(HarvestDetailVM harvestDetailVM) {
        Harvest harvest = harvestRepository.findById(harvestDetailVM.getHarvestId())
                .orElseThrow(() -> new RuntimeException("Harvest not found"));

        Tree tree = treeRepository.findById(harvestDetailVM.getTreeId())
                .orElseThrow(() -> new RuntimeException("Tree not found"));

        int treeAge = Period.between(tree.getPlantingDate(), LocalDate.now()).getYears();
        if(treeAge > 20){
            throw new RuntimeException("Tree is too old to be harvested");
        }

        if(harvestDetailRepository.existsByTree_IdAndHarvest_Season(tree.getId(), harvest.getSeason())){
            throw new RuntimeException("Tree is already harvested for this season");
        }
        HarvestDetail harvestDetail = harvestDetailMapper.harvestDetailVMToHarvestDetail(harvestDetailVM);
        harvestDetail.setHarvest(harvest);
        harvestDetail.setTree(tree);

        harvest.setTotalQuantity(harvest.getTotalQuantity() + harvestDetail.getQuantityHarvested());
        harvestRepository.save(harvest);

        HarvestDetail savedDetail = harvestDetailRepository.save(harvestDetail);
        return harvestDetailMapper.harvestDetailToHarvestDetailVM(savedDetail);
    }

    @Override
    public List<HarvestDetailVM> getAllHarvestDetails() {
        List<HarvestDetail> details = harvestDetailRepository.findAll();
        return details.stream().map(harvestDetailMapper::harvestDetailToHarvestDetailVM).collect(Collectors.toList());
    }

    @Override
    public HarvestDetailVM getHarvestDetailById(Long id) {
        HarvestDetail detail = harvestDetailRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Harvest detail not found"));
        return harvestDetailMapper.harvestDetailToHarvestDetailVM(detail);
    }

    @Override
    public void deleteHarvestDetail(Long id) {
        HarvestDetail detail = harvestDetailRepository.findById(id)
                        .orElseThrow(() -> new RuntimeException("Harvest detail not found"));

        Harvest harvest = detail.getHarvest();
        harvest.setTotalQuantity(harvest.getTotalQuantity() - detail.getQuantityHarvested());
        harvestRepository.save(harvest);
        harvestDetailRepository.delete(detail);
    }
}
