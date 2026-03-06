package org.celtek.cuisineapi.infrastructure.web.mapper;

import org.celtek.cuisineapi.domain.model.Recipe;
import org.celtek.cuisine.dto.RecipeDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RecipeMapper {

    // On ignore SEULEMENT la date de création
    @Mapping(target = "createdAt", ignore = true)
    Recipe toDomain(RecipeDTO dto);

    RecipeDTO toDto(Recipe domain);
}