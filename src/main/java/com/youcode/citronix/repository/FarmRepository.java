package com.youcode.citronix.repository;

import com.youcode.citronix.entity.Farm;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FarmRepository extends JpaRepository<Farm,Long> , CustomFarmRepository {

}
