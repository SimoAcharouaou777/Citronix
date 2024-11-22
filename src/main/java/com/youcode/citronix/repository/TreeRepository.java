package com.youcode.citronix.repository;

import com.youcode.citronix.entity.Tree;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TreeRepository extends JpaRepository<Tree,Long> {
    @Query("SELECT COUNT (t) FROM Tree  t WHERE t.field.id = :fieldId")
    long countByFieldId(@Param("fieldId") Long fieldId);

    List<Tree> findByField_Id(Long fieldId);

}
