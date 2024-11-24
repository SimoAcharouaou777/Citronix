package com.youcode.citronix.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "harvests")
@Getter
@Setter
public class Harvest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "season" , nullable = false)
    private Season season;

    @Column(name = "harvest_date" , nullable = false)
    private LocalDate harvestDate;

    @Column(name = "total_quantity" , nullable = false)
    private Double totalQuantity = 0.0;

    @OneToMany(mappedBy = "harvest", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<HarvestDetail> harvestDetails;

    public void addToTotalQuantity(Double quantity) {
        if(quantity == null || quantity < 0) {
            throw new IllegalArgumentException("Quantity must be a positive number");
        }
        this.totalQuantity += quantity;
    }
    public void subtractFromTotalQuantity(Double quantity) {
        if(quantity == null || quantity < 0) {
            throw new IllegalArgumentException("Quantity must be a positive number");
        }
        this.totalQuantity -= quantity;
        if(this.totalQuantity < 0) {
            this.totalQuantity = 0.0;
        }
    }

}
