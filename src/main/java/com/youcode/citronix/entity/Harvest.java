package com.youcode.citronix.entity;

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

    @ManyToOne
    @JoinColumn(name = "field_id" , nullable = false)
    private Field field;

    @Enumerated(EnumType.STRING)
    @Column(name = "season" , nullable = false)
    private Season season;

    @Column(name = "harvest_date" , nullable = false)
    private LocalDate harvestDate;

    @Column(name = "total_quantity" , nullable = false)
    private Double totalQuantity;

    @OneToMany(mappedBy = "harvest", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<HarvestDetail> harvestDetails;


}
