package com.youcode.citronix.service;

import com.youcode.citronix.dto.TreeDTO;
import com.youcode.citronix.entity.Field;
import com.youcode.citronix.entity.Tree;
import com.youcode.citronix.mapper.TreeMapper;
import com.youcode.citronix.repository.FieldRepository;
import com.youcode.citronix.repository.TreeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

@Service
@Transactional
public class TreeServiceImpl implements TreeService{

    @Autowired
    private TreeRepository treeRepository;

    @Autowired
    private FieldRepository fieldRepository;

    private final TreeMapper treeMapper = TreeMapper.INSTANCE;

    @Override
    public TreeDTO createTree(Long FieldId, TreeDTO treeDTO) {
        Field field = fieldRepository.findById(FieldId)
                .orElseThrow(() -> new RuntimeException("Field not found"));
        Tree tree = treeMapper.treeDTOToTree(treeDTO);
        tree.setField(field);

        tree.setAge(calculateAge(tree.getPlantingDate()));
        tree.setProductivityRate(calculateProductivityRate(tree.getAge()));

        Tree savedTree = treeRepository.save(tree);
        return treeMapper.treeToTreeDTO(savedTree);
    }

    @Override
    public TreeDTO getTreeById(Long id) {
        Tree tree = treeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tree not found"));
        return treeMapper.treeToTreeDTO(tree);
    }

    @Override
    public TreeDTO updateTree(Long treeId, TreeDTO treeDTO) {
        Tree tree = treeRepository.findById(treeId)
                .orElseThrow(() -> new RuntimeException("Tree not found"));

        tree.setPlantingDate(treeDTO.getPlantingDate());
        tree.setField(fieldRepository.findById(treeDTO.getFieldId())
                .orElseThrow(() -> new RuntimeException("Field not found")));
        tree.setAge(calculateAge(tree.getPlantingDate()));
        tree.setProductivityRate(calculateProductivityRate(tree.getAge()));

        Tree updatedTree = treeRepository.save(tree);
        return treeMapper.treeToTreeDTO(updatedTree);
    }

    @Override
    public List<TreeDTO> getAllTrees() {
        List<Tree> trees = treeRepository.findAll();
        return treeMapper.treesToTreeDTOs(trees);
    }

    @Override
    public void deleteTree(Long id) {
        Tree tree = treeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tree not found"));
        treeRepository.delete(tree);
    }

    private Integer calculateAge(LocalDate plantingDate){
        if(plantingDate == null){
            throw new IllegalArgumentException("Planting date cannot be null ");
        }

        Period period = Period.between(plantingDate, LocalDate.now());
        return period.getYears();
    }
    private Double calculateProductivityRate(Integer age){
        if(age == null){
            throw new IllegalArgumentException("Age cannot be null");
        }
        if(age < 3){
            return 2.5;
        } else if (age >= 3 && age <= 10) {
            return 12.0;
        }else {
            return 20.0;
        }
    }
}
