package com.youcode.citronix.service;

import com.youcode.citronix.entity.Sale;
import com.youcode.citronix.vm.SaleVM;

import java.util.List;

public interface SaleService {
    SaleVM createSale(SaleVM saleVM);
    List<SaleVM> getAllSales();
    SaleVM getSaleById(Long id);
    void deleteSale(Long id);
}
