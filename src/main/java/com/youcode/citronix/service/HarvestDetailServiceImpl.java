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

        HarvestDetail harvestDetail = new HarvestDetail();
        harvestDetail.setHarvest(harvest);
        harvestDetail.setTree(tree);
        harvestDetail.setQuantityHarvested(harvestDetailVM.getQuantityHarvested());
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

        harvestDetailRepository.delete(detail);
    }

    @Override
    public List<HarvestDetailVM> getHarvestDetailsByHarvestId(Long harvestId) {
        return harvestDetailRepository.findAllByHarvestId(harvestId)
                .stream()
                .map(harvestDetailMapper::harvestDetailToHarvestDetailVM)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteHarvestDetailsByHarvestId(Long harvestId) {
        List<HarvestDetail> details = harvestDetailRepository.findAllByHarvestId(harvestId);
        harvestDetailRepository.deleteAll(details);
    }

}
