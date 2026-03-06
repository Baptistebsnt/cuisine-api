package org.celtek.cuisineapi.infrastructure.persistence.adapter;

import lombok.RequiredArgsConstructor;
import org.celtek.cuisineapi.domain.model.Ingredient;
import org.celtek.cuisineapi.domain.model.Recipe;
import org.celtek.cuisineapi.domain.model.Step;
import org.celtek.cuisineapi.domain.port.RecipeRepository;
import org.celtek.cuisineapi.infrastructure.mapper.RecipePersistenceMapper;
import org.celtek.cuisineapi.infrastructure.persistence.entity.IngredientEntity;
import org.celtek.cuisineapi.infrastructure.persistence.entity.RecipeEntity;
import org.celtek.cuisineapi.infrastructure.persistence.entity.RecipeIngredientEntity;
import org.celtek.cuisineapi.infrastructure.persistence.entity.StepEntity;
import org.celtek.cuisineapi.infrastructure.persistence.repository.SpringDataIngredientRepository;
import org.celtek.cuisineapi.infrastructure.persistence.repository.SpringDataRecipeRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class RecipeRepositoryAdapter implements RecipeRepository {

    private final SpringDataRecipeRepository recipeRepository;
    private final SpringDataIngredientRepository ingredientRepository;
    private final RecipePersistenceMapper mapper;

    @Override
    @Transactional
    public Recipe save(Recipe recipe) {
        RecipeEntity entity = mapper.toEntity(recipe);

        if (recipe.getSteps() != null) {
            var stepEntities = recipe.getSteps().stream().map(step -> StepEntity.builder().stepOrder(step.getStepOrder()).instruction(step.getInstruction()).recipe(entity).build()).toList();
            entity.setSteps(stepEntities);
        }

        if (recipe.getIngredients() != null) {
            var recipeIngredients = recipe.getIngredients().stream().map(ing -> {
                IngredientEntity dictIng = ingredientRepository.findByNameIgnoreCase(ing.getName()).orElseGet(() -> ingredientRepository.save(IngredientEntity.builder().name(ing.getName().toLowerCase()).build()));

                return RecipeIngredientEntity.builder().ingredient(dictIng).quantity(ing.getQuantity()).unit(ing.getUnit()).recipe(entity).build();
            }).toList();
            entity.setIngredients(recipeIngredients);
        }

        RecipeEntity savedEntity = recipeRepository.save(entity);

        return mapToDomainCompleto(savedEntity);
    }

    @Override
    public Optional<Recipe> findById(Long id) {
        return recipeRepository.findById(id).map(this::mapToDomainCompleto);
    }

    // Méthode d'aide pour reconstruire l'objet métier complet
    private Recipe mapToDomainCompleto(RecipeEntity entity) {
        Recipe domain = mapper.toDomain(entity);

        if (entity.getSteps() != null) {
            domain.setSteps(entity.getSteps().stream().map(s -> Step.builder().stepOrder(s.getStepOrder()).instruction(s.getInstruction()).build()).toList());
        }

        if (entity.getIngredients() != null) {
            domain.setIngredients(entity.getIngredients().stream().map(ri -> Ingredient.builder().name(ri.getIngredient().getName()).quantity(ri.getQuantity()).unit(ri.getUnit()).build()).toList());
        }
        return domain;
    }
}