package com.youcode.citronix.controller;

import com.youcode.citronix.service.SaleService;
import com.youcode.citronix.service.SalesServiceImpl;
import com.youcode.citronix.vm.SaleVM;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sales")
public class SaleController {

    @Autowired
    private SalesServiceImpl salesServiceImpl;

    @PostMapping
    public ResponseEntity<SaleVM> createSale(@RequestBody SaleVM saleVM){
        SaleVM createdSale = salesServiceImpl.createSale(saleVM);
        return new ResponseEntity<>(createdSale, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<SaleVM>> getAllSales(){
        List<SaleVM> sales = salesServiceImpl.getAllSales();
        return new ResponseEntity<>(sales, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SaleVM> getSaleById(@PathVariable Long id){
        SaleVM sale = salesServiceImpl.getSaleById(id);
        return new ResponseEntity<>(sale, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSale(@PathVariable Long id){
        salesServiceImpl.deleteSale(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
