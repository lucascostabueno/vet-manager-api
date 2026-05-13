package br.com.lucascostabueno.vetmanager.api.modules.setting.user.application.mapper;

import br.com.lucascostabueno.vetmanager.api.modules.setting.user.application.dto.UserCreateRequest;
import br.com.lucascostabueno.vetmanager.api.modules.setting.user.application.dto.UserResponse;
import br.com.lucascostabueno.vetmanager.api.modules.setting.user.application.dto.UserUpdateRequest;
import br.com.lucascostabueno.vetmanager.api.modules.setting.user.domain.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "id", ignore = true)
    User toEntity(UserCreateRequest request);

    UserResponse toResponse(User entity);

    void updateEntityFromDto(UserUpdateRequest dto, @MappingTarget User entity);
}
