package com.youcode.citronix.service;

import com.youcode.citronix.dto.TreeDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TreeService {

    TreeDTO createTree(Long FieldId, TreeDTO treeDTO);

    TreeDTO getTreeById(Long id);

    TreeDTO updateTree(Long id, TreeDTO treeDTO);

    List<TreeDTO> getAllTrees();

    void deleteTree(Long id);


}
