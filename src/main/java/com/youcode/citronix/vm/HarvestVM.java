package com.youcode.citronix.vm;

import com.youcode.citronix.entity.Season;
import jakarta.validation.constraints.PastOrPresent;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class HarvestVM {

    private Long id;
//    private Long fieldId;
    private Season season;
    @PastOrPresent(message = "harvest date cannot be in the future")
    private LocalDate harvestDate;
    private Double totalQuantity;

}
