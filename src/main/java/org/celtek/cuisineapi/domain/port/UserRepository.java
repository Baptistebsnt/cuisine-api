package org.celtek.cuisineapi.domain.port;

import org.celtek.cuisineapi.domain.model.User;
import java.util.Optional;

public interface UserRepository {
    User save(User user);
    Optional<User> findByEmail(String email);
    boolean existsByEmail(String email);
}