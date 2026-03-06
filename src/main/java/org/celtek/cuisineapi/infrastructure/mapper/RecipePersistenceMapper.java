package org.celtek.cuisineapi.infrastructure.mapper;

import org.celtek.cuisineapi.domain.model.Recipe;
import org.celtek.cuisineapi.infrastructure.persistence.entity.RecipeEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RecipePersistenceMapper {

    // Transforme l'entité BDD en modèle métier
    Recipe toDomain(RecipeEntity entity);

    // Transforme le modèle métier en entité BDD
    RecipeEntity toEntity(Recipe domain);
}
