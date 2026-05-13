package br.com.lucascostabueno.vetmanager.api.modules.setting.user.domain.service;

import br.com.lucascostabueno.vetmanager.api.modules.setting.user.application.dto.UserCreateRequest;
import br.com.lucascostabueno.vetmanager.api.modules.setting.user.application.dto.UserResponse;
import br.com.lucascostabueno.vetmanager.api.modules.setting.user.application.dto.UserSearchFilter;
import br.com.lucascostabueno.vetmanager.api.modules.setting.user.application.dto.UserUpdateRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface UserService {
    UserResponse findById(UUID id);

    UserResponse create(UserCreateRequest request);

    UserResponse update(UUID id, UserUpdateRequest request);

    Page<UserResponse> search(UserSearchFilter filter, Pageable pageable);

    void delete(UUID id);
}
