package com.youcode.citronix.repository;

import com.youcode.citronix.entity.Field;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FieldRepository extends JpaRepository<Field,Long> {
}
