package com.youcode.citronix.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "trees")
@Getter
@Setter
public class Tree {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "field_id" , nullable = false)
    private Field field;

    @Column(name = "planting_date" , nullable = false)
    private LocalDate plantingDate;

    @Column(name = "age" , nullable = false)
    private Integer age;

    @Column(name = "productivity_rate" , nullable = false)
    private Double productivityRate;
}
