package org.celtek.cuisineapi.domain.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class Ingredient {
    private String name;
    private Double quantity;
    private String unit;
}
