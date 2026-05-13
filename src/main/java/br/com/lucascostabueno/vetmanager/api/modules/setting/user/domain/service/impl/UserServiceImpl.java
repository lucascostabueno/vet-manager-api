package br.com.lucascostabueno.vetmanager.api.modules.setting.user.domain.service.impl;

import br.com.lucascostabueno.vetmanager.api.modules.setting.user.application.dto.UserCreateRequest;
import br.com.lucascostabueno.vetmanager.api.modules.setting.user.application.dto.UserResponse;
import br.com.lucascostabueno.vetmanager.api.modules.setting.user.application.dto.UserSearchFilter;
import br.com.lucascostabueno.vetmanager.api.modules.setting.user.application.dto.UserUpdateRequest;
import br.com.lucascostabueno.vetmanager.api.modules.setting.user.application.mapper.UserMapper;
import br.com.lucascostabueno.vetmanager.api.modules.setting.user.domain.repository.UserRepository;
import br.com.lucascostabueno.vetmanager.api.modules.setting.user.domain.service.UserService;
import br.com.lucascostabueno.vetmanager.api.modules.setting.user.infrastructure.persistence.specification.UserSpecs;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository repository;
    private final UserMapper mapper;

    @Override
    @Transactional(readOnly = true)
    public UserResponse findById(UUID id) {
        return repository.findById(id)
                .map(mapper::toResponse)
                .orElseThrow(() -> new RuntimeException("User not found."));
    }

    @Transactional
    public UserResponse create(UserCreateRequest request) {
        var employee = mapper.toEntity(request);
        return mapper.toResponse(repository.save(employee));
    }

    @Transactional
    public UserResponse update(UUID id, UserUpdateRequest request) {
        var employee = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found."));

        mapper.updateEntityFromDto(request, employee);
        return mapper.toResponse(repository.save(employee));
    }

    @Transactional(readOnly = true)
    public Page<UserResponse> search(UserSearchFilter filter, Pageable pageable) {
        return repository.findAll(UserSpecs.byFilter(filter), pageable)
                .map(mapper::toResponse);
    }

    @Transactional
    public void delete(UUID id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("Invalid ID.");
        }
        repository.deleteById(id);
    }
}
