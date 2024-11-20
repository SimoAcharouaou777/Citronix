package com.youcode.citronix.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class FarmDTO {
    private Long id;
    private String name;
    private String location;
    private Double size;
    private LocalDate creationDate;
}
