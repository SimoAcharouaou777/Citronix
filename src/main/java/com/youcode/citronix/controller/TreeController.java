package com.youcode.citronix.controller;

import com.youcode.citronix.dto.TreeDTO;
import com.youcode.citronix.entity.Tree;
import com.youcode.citronix.service.TreeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/trees")
public class TreeController {

    @Autowired
    private TreeServiceImpl treeServiceImpl;

    @PostMapping("/{FieldId}")
    public ResponseEntity<TreeDTO> createTree(@PathVariable Long FieldId , @RequestBody TreeDTO treeDTO){
        TreeDTO createdTree = treeServiceImpl.createTree(FieldId, treeDTO);
        return new ResponseEntity<>(createdTree, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<TreeDTO>> getAllTrees(){
        List<TreeDTO> trees = treeServiceImpl.getAllTrees();
        return new ResponseEntity<>(trees, HttpStatus.OK);
    }

    @GetMapping("/{treeId}")
    public ResponseEntity<TreeDTO> getTreeById(@PathVariable Long treeId){
        TreeDTO tree = treeServiceImpl.getTreeById(treeId);
        return new ResponseEntity<>(tree, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TreeDTO> updateTree(@PathVariable Long id, @RequestBody TreeDTO treeDTO){
        TreeDTO updatedTree = treeServiceImpl.updateTree(id, treeDTO);
        return new ResponseEntity<>(updatedTree, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTree(@PathVariable Long id){
        treeServiceImpl.deleteTree(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
