package com.youcode.citronix.repository;

import com.youcode.citronix.entity.Farm;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Repository
public class CustomFarmRepositoryImpl implements CustomFarmRepository{

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Farm> searchFarmsWithCriteria(String name, String location, Double minSize, Double maxSize, LocalDate creationDate){
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Farm> query = cb.createQuery(Farm.class);
        Root<Farm> farm = query.from(Farm.class);
        List<Predicate> predicates = new ArrayList<>();

        if (name != null) {
            predicates.add(cb.like(cb.lower(farm.get("name")), "%" + name.toLowerCase() + "%"));
        }
        if (location != null) {
            predicates.add(cb.like(cb.lower(farm.get("location")), "%" + location.toLowerCase() + "%"));
        }
        if (minSize != null) {
            predicates.add(cb.greaterThanOrEqualTo(farm.get("size"), minSize));
        }
        if (maxSize != null) {
            predicates.add(cb.lessThanOrEqualTo(farm.get("size"), maxSize));
        }
        if (creationDate != null) {
            predicates.add(cb.equal(farm.get("creationDate"), creationDate));
        }
        query.where(cb.and(predicates.toArray(new Predicate[0])));
        return entityManager.createQuery(query).getResultList();
    }
}
