package org.celtek.cuisineapi.infrastructure.mapper;

import org.celtek.cuisineapi.domain.model.User;
import org.celtek.cuisineapi.infrastructure.persistence.entity.UserEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserPersistenceMapper {
    User toDomain(UserEntity entity);
    UserEntity toEntity(User domain);
}
