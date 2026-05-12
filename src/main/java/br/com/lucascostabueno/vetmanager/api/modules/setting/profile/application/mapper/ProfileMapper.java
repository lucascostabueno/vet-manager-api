package br.com.lucascostabueno.vetmanager.api.modules.setting.profile.application.mapper;

import br.com.lucascostabueno.vetmanager.api.modules.setting.profile.application.dto.ProfileCreateRequest;
import br.com.lucascostabueno.vetmanager.api.modules.setting.profile.application.dto.ProfileResponse;
import br.com.lucascostabueno.vetmanager.api.modules.setting.profile.application.dto.ProfileUpdateRequest;
import br.com.lucascostabueno.vetmanager.api.modules.setting.profile.domain.model.Profile;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ProfileMapper {

    @Mapping(target = "id", ignore = true)
    Profile toEntity(ProfileCreateRequest request);

    ProfileResponse toResponse(Profile entity);

    void updateEntityFromDto(ProfileUpdateRequest dto, @MappingTarget Profile entity);
}
