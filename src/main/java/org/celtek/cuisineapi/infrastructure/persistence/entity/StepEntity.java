package org.celtek.cuisineapi.infrastructure.persistence.entity;

import jakarta.persistence.Embeddable;
import lombok.*;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StepEntity {
    private Integer stepOrder;
    private String instruction;
}
