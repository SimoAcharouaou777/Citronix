package com.youcode.citronix.service;

import com.youcode.citronix.entity.Harvest;
import com.youcode.citronix.entity.Sale;
import com.youcode.citronix.mapper.SaleMapper;
import com.youcode.citronix.repository.HarvestRepository;
import com.youcode.citronix.repository.SaleRepository;
import com.youcode.citronix.vm.SaleVM;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SalesServiceImpl implements SaleService{

    @Autowired
    private SaleRepository saleRepository;

    @Autowired
    private HarvestRepository harvestRepository;

    private final SaleMapper saleMapper = SaleMapper.INSTANCE;

    @Override
    public SaleVM createSale(SaleVM saleVM) {
        Harvest harvest = harvestRepository.findById(saleVM.getHarvestId())
                .orElseThrow(() -> new RuntimeException("Harvest not found"));

        double totalQuantitySold = saleRepository.findAll()
                .stream()
                .filter(sale -> sale.getHarvest().getId().equals(harvest.getId()))
                .mapToDouble(Sale::getQuantitySold)
                .sum();
        if(totalQuantitySold + saleVM.getQuantitySold() > harvest.getTotalQuantity()){
            throw new RuntimeException("Total quantity sold exceeds total quantity harvested");
        }

        Sale sale = saleMapper.saleVMToSale(saleVM);
        sale.setHarvest(harvest);

        Sale savedSale = saleRepository.save(sale);
        return saleMapper.saleToSaleVM(savedSale);
    }

    @Override
    public List<SaleVM> getAllSales() {
        List<Sale> sales = saleRepository.findAll();
        return sales.stream().map(saleMapper::saleToSaleVM).collect(Collectors.toList());
    }

    @Override
    public SaleVM getSaleById(Long id) {
        Sale sale = saleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Sale not found"));
        return saleMapper.saleToSaleVM(sale);
    }

    @Override
    public void deleteSale(Long id) {
        Sale sale = saleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Sale not found"));
        saleRepository.delete(sale);
    }
}
