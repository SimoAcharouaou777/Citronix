package com.youcode.citronix.repository;

import com.youcode.citronix.entity.HarvestDetail;
import com.youcode.citronix.entity.Season;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HarvestDetailRepository extends JpaRepository<HarvestDetail,Long> {
    boolean existsByTree_IdAndHarvest_Season(Long treeId, Season season);

    @Query("SELECT hd FROM HarvestDetail hd WHERE hd.harvest.id = :harvestId")
    List<HarvestDetail> findAllByHarvestId(@Param("harvestId") Long harvestId);
}
