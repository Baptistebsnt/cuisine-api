package org.celtek.cuisineapi.domain.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder
public class Recipe {
    private Long id;
    private String title;
    private String description;
    private Integer cookingTimeMinutes;
    private Long authorId;
    private LocalDateTime createdAt;

    private List<Ingredient> ingredients;
    private List<Step> steps;

    public boolean isValid() {
        return title != null && !title.isBlank() && cookingTimeMinutes > 0;
    }
}
