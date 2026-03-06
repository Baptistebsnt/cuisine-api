package org.celtek.cuisineapi.infrastructure.persistence.adapter;

import lombok.RequiredArgsConstructor;
import org.celtek.cuisineapi.domain.model.Recipe;
import org.celtek.cuisineapi.domain.port.RecipeRepository;
import org.celtek.cuisineapi.infrastructure.mapper.RecipePersistenceMapper;
import org.celtek.cuisineapi.infrastructure.persistence.repository.SpringDataRecipeRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor // Lombok injecte automatiquement les dépendances !
public class RecipeRepositoryAdapter implements RecipeRepository {

    private final SpringDataRecipeRepository springDataRepository;
    private final RecipePersistenceMapper mapper;

    @Override
    public Recipe save(Recipe recipe) {
        // 1. On traduit le modèle métier en Entité JPA
        var entity = mapper.toEntity(recipe);
        // 2. On sauvegarde en BDD avec Spring Data
        var savedEntity = springDataRepository.save(entity);
        // 3. On retraduit l'Entité sauvegardée en modèle métier pour la renvoyer
        return mapper.toDomain(savedEntity);
    }

    @Override
    public Optional<Recipe> findById(Long id) {
        return springDataRepository.findById(id)
                .map(mapper::toDomain);
    }
}