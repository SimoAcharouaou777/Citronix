package com.youcode.citronix.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "sales")
@Getter
@Setter
public class Sale {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "harvest_id", nullable = false)
    private Harvest harvest;

    @Column(name = "client_name" , nullable = false)
    private String clientName;

    @Column(name = "sale_date" , nullable = false)
    private LocalDate saleDate;

    @Column(name = "unit_price" , nullable = false)
    private Double unitPrice;

    @Column(name = "total_revenue" , nullable = false)
    private Double totalRevenue;
}
