package com.youcode.citronix.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class TreeDTO {

    private Long id;
    @NotNull(message = "Field id cannot be null")
    private Long fieldId;
    @NotNull(message = "Tree type cannot be null")
    @PastOrPresent(message = "Planting date must be in the past or present")
    private LocalDate plantingDate;
    @Min(value = 0, message = "Tree age must be at least 0 and cannot be negative")
    private Integer age;
    private Double productivityRate;
}
