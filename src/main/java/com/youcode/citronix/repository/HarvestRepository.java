package com.youcode.citronix.repository;

import com.youcode.citronix.entity.Harvest;
import com.youcode.citronix.entity.Season;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HarvestRepository extends JpaRepository<Harvest, Long> {

    @Query("SELECT COUNT(h) FROM Harvest h " +
            "JOIN HarvestDetail hd ON hd.harvest.id = h.id " +
            "JOIN Tree t ON t.id = hd.tree.id " +
            "WHERE t.field.id = :fieldId AND h.season = :season")
    long countByFieldAndSeason(@Param("fieldId") Long fieldId, @Param("season") Season season);

    @Query("SELECT COUNT(h) > 0 FROM Harvest h " +
            "JOIN HarvestDetail hd ON hd.harvest.id = h.id " +
            "JOIN Tree t ON t.id = hd.tree.id " +
            "WHERE t.field.id = :fieldId AND h.season = :season AND EXTRACT(YEAR FROM h.harvestDate) = :year")
    boolean existsByFieldIdAndSeasonAndHarvestDateYear(@Param("fieldId") Long fieldId, @Param("season") Season season, @Param("year") int year);

    @Query("SELECT DISTINCT h FROM Harvest h " +
            "JOIN HarvestDetail hd ON hd.harvest.id = h.id " +
            "JOIN Tree t ON t.id = hd.tree.id " +
            "WHERE t.field.id = :fieldId")
    List<Harvest> findByFieldId(@Param("fieldId") Long fieldId);
}
