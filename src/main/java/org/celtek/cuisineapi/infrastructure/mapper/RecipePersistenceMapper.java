package org.celtek.cuisineapi.infrastructure.mapper;

import org.celtek.cuisineapi.domain.model.Recipe;
import org.celtek.cuisineapi.infrastructure.persistence.entity.RecipeEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RecipePersistenceMapper {

    @Mapping(target = "ingredients", ignore = true)
    @Mapping(target = "steps", ignore = true)
    RecipeEntity toEntity(Recipe domain);

    @Mapping(target = "ingredients", ignore = true)
    @Mapping(target = "steps", ignore = true)
    Recipe toDomain(RecipeEntity entity);
}
