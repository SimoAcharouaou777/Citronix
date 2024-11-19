package com.youcode.citronix.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FarmDTO {
    private Long id;
    private String name;
    private String location;
    private Double size;
    private String creationDate;
}
