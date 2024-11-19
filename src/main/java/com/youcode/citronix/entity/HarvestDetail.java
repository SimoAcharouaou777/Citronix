package com.youcode.citronix.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "harvest_details")
@Getter
@Setter
public class HarvestDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "tree_id" , nullable = false)
    private Tree tree;

    @ManyToOne
    @JoinColumn(name = "harvest_id" , nullable = false)
    private Harvest harvest;

    @Column(name = "quantity_harvested" , nullable = false)
    private Double quantityHarvested;
}
