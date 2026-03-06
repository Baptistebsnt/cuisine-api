package org.celtek.cuisineapi.domain.port;

import org.celtek.cuisineapi.domain.model.Recipe;
import java.util.Optional;

public interface RecipeRepository {
    Recipe save(Recipe recipe);
    Optional<Recipe> findById(Long id);
}
