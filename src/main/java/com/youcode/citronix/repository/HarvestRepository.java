package com.youcode.citronix.repository;

import com.youcode.citronix.entity.Harvest;
import com.youcode.citronix.entity.Season;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HarvestRepository extends JpaRepository<Harvest,Long> {

    @Query("SELECT COUNT (h) FROM  Harvest h WHERE h.field.id = :fieldId AND h.season = :season")
    long countByFieldAndSeason(@Param("fieldId") Long fieldId, @Param("season")Season season);

    boolean existsByField_IdAndSeason(Long fieldId, Season season);

    @Query("SELECT  COUNT (h) > 0 FROM Harvest h WHERE h.field.id = :fieldId AND h.season = :season AND YEAR(h.harvestDate) = :year")
    boolean existsByField_IdAndSeasonAndHarvestDateYear(@Param("fieldId") Long fieldId, @Param("season") Season season, @Param("year") int Year);

    List<Harvest> findByField_Id(Long fieldId);
}
