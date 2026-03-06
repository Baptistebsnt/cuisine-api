package org.celtek.cuisineapi.application;

import lombok.RequiredArgsConstructor;
import org.celtek.cuisineapi.domain.model.Recipe;
import org.celtek.cuisineapi.domain.port.RecipeRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RecipeService {

    private final RecipeRepository recipeRepository;

    public Recipe createRecipe(Recipe recipe) {
        // C'est ici que tu pourras ajouter ta logique métier plus tard
        // Exemple : if (!recipe.isValid()) throw new BusinessException("Recette invalide");

        return recipeRepository.save(recipe);
    }
}