package com.youcode.citronix.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class TreeDTO {

    private Long id;
    private Long fieldId;
    private LocalDate plantingDate;
    private Integer age;
    private Double productivityRate;
}
