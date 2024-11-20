package com.youcode.citronix.dto;

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
    private Double size;
}
