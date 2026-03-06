package org.celtek.cuisineapi.infrastructure.web.mapper;

import org.celtek.cuisineapi.domain.model.User;
import org.celtek.cuisine.dto.UserRegistrationDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "role", ignore = true)
    User toDomain(UserRegistrationDTO dto);
}