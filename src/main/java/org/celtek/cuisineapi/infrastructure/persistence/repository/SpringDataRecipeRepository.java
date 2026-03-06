package org.celtek.cuisineapi.infrastructure.persistence.repository;

import org.celtek.cuisineapi.infrastructure.persistence.entity.RecipeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpringDataRecipeRepository extends JpaRepository<RecipeEntity, Long> {
}
