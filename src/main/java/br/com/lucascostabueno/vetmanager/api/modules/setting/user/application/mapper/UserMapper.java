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
  @Mapping(target = "profile", ignore = true)
  @Mapping(target = "employee", ignore = true)
  User toEntity(UserCreateRequest request);

  @Mapping(target = "employeeId", source = "employee.id")
  UserResponse toResponse(User entity);

  @Mapping(target = "id", ignore = true)
  @Mapping(target = "password", ignore = true)
  @Mapping(target = "profile", ignore = true)
  @Mapping(target = "employee", ignore = true)
  void updateEntity(UserUpdateRequest dto, @MappingTarget User entity);
}
