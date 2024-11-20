package com.youcode.citronix.repository;

import com.youcode.citronix.entity.Farm;

import java.time.LocalDate;
import java.util.List;

public interface CustomFarmRepository {
    List<Farm> searchFarmsWithCriteria(String name, String location, Double minSize, Double maxSize, LocalDate creationDate);
}
