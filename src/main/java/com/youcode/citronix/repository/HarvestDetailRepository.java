package com.youcode.citronix.repository;

import com.youcode.citronix.entity.HarvestDetail;
import com.youcode.citronix.entity.Season;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HarvestDetailRepository extends JpaRepository<HarvestDetail,Long> {
    boolean existsByTree_IdAndHarvest_Season(Long treeId, Season season);
}
