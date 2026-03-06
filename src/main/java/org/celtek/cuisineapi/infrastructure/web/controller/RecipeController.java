package org.celtek.cuisineapi.infrastructure.web.controller;

import lombok.RequiredArgsConstructor;
import org.celtek.cuisine.api.RecipesApi;
import org.celtek.cuisine.dto.RecipeDTO;
import org.celtek.cuisineapi.application.RecipeService;
import org.celtek.cuisineapi.infrastructure.web.mapper.RecipeMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class RecipeController implements RecipesApi {

    private final RecipeService recipeService;
    private final RecipeMapper mapper;

    // Cette méthode override (surcharge) celle de l'interface générée par Swagger
    @Override
    public ResponseEntity<RecipeDTO> createRecipe(RecipeDTO recipeDTO) {

        // 1. On traduit le DTO du Web en objet pur du Domaine
        var domainRecipe = mapper.toDomain(recipeDTO);

        // 2. On passe le bébé au Service (qui va le sauvegarder via le Repository)
        var savedRecipe = recipeService.createRecipe(domainRecipe);

        // 3. On traduit le résultat (qui vient de la BDD) en DTO pour le renvoyer à l'utilisateur
        var responseDTO = mapper.toDto(savedRecipe);

        return ResponseEntity.ok(responseDTO);
    }
}
