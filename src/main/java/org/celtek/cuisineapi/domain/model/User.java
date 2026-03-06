package org.celtek.cuisineapi.domain.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class User {
    private Long id;
    private String email;
    private String password;
    private String pseudo;
    private String role;
}
