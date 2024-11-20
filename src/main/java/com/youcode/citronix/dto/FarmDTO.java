package com.youcode.citronix.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class FarmDTO {
    private Long id;

    @NotBlank(message = "Farm name cannot be empty")
    private String name;

    @NotBlank(message = "Farm location cannot be empty")
    private String location;

    @NotNull(message = "Farm size cannot be null")
    @Positive(message = "Farm size must be positive")
    private Double size;

    @NotNull(message = "Farm creation date cannot be null")
    @PastOrPresent(message = "Farm creation date must not be in the future ")
    private LocalDate creationDate;
}
