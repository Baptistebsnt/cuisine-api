package org.celtek.cuisineapi.domain.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class Step {
    private Integer stepOrder;
    private String instruction;
}