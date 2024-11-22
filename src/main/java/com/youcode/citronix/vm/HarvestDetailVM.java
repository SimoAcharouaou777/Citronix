package com.youcode.citronix.vm;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HarvestDetailVM {
    private Long id;
    private Long harvestId;
    private Long treeId;
    private Double quantityHarvested;
}
