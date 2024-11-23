package com.youcode.citronix.vm;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class SaleVM {
    private Long id;
    private Long harvestId;
    private String clientName;
    private LocalDate saleDate;
    private Double unitPrice;
    private Double quantitySold;

    public Double getTotalRevenue(){
        return this.quantitySold * this.unitPrice;
    }
}
