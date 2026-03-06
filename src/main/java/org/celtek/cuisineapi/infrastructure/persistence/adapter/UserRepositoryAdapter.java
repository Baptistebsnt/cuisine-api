package org.celtek.cuisineapi.infrastructure.persistence.adapter;

import lombok.RequiredArgsConstructor;
import org.celtek.cuisineapi.domain.model.User;
import org.celtek.cuisineapi.domain.port.UserRepository;
import org.celtek.cuisineapi.infrastructure.persistence.repository.SpringDataUserRepository;
import org.celtek.cuisineapi.infrastructure.mapper.UserPersistenceMapper;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UserRepositoryAdapter implements UserRepository {

    private final SpringDataUserRepository repository;
    private final UserPersistenceMapper mapper;

    @Override
    public User save(User user) {
        var entity = mapper.toEntity(user);
        return mapper.toDomain(repository.save(entity));
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return repository.findByEmail(email).map(mapper::toDomain);
    }

    @Override
    public boolean existsByEmail(String email) {
        return repository.existsByEmail(email);
    }
}
