package org.celtek.cuisineapi.infrastructure.persistence.entity;

import jakarta.persistence.Embeddable;
import lombok.*;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class IngredientEntity {
    private String name;
    private Double quantity;
    private String unit;
}
