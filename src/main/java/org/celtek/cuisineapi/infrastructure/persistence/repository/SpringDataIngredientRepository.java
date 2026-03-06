package org.celtek.cuisineapi.infrastructure.persistence.repository;

import org.celtek.cuisineapi.infrastructure.persistence.entity.IngredientEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SpringDataIngredientRepository extends JpaRepository<IngredientEntity, Long> {
    Optional<IngredientEntity> findByNameIgnoreCase(String name);
}
