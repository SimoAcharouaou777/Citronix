package com.youcode.citronix.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FieldDTO {

    private Long id;
    private Long farmId;

    @NotNull(message = "Field size cannot be null")
    @Positive(message = "Field size must be greater than 0")
    @DecimalMin(value = "0.1", message = "Field size ust be at least 0.1 hectare")
    private Double size;
}

