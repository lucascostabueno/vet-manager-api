package br.com.lucascostabueno.vetmanager.api.modules.setting.user.domain.service.impl;

import br.com.lucascostabueno.vetmanager.api.modules.setting.user.application.dto.UserCreateRequest;
import br.com.lucascostabueno.vetmanager.api.modules.setting.user.application.dto.UserResponse;
import br.com.lucascostabueno.vetmanager.api.modules.setting.user.application.dto.UserSearchFilter;
import br.com.lucascostabueno.vetmanager.api.modules.setting.user.application.dto.UserUpdateRequest;
import br.com.lucascostabueno.vetmanager.api.modules.setting.user.application.mapper.UserMapper;
import br.com.lucascostabueno.vetmanager.api.modules.setting.user.domain.model.User;
import br.com.lucascostabueno.vetmanager.api.modules.setting.user.domain.repository.UserRepository;
import br.com.lucascostabueno.vetmanager.api.modules.setting.user.domain.service.UserService;
import br.com.lucascostabueno.vetmanager.api.modules.setting.user.infrastructure.persistence.specification.UserSpecs;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

  private final UserRepository userRepository;
  private final UserMapper mapper;
  private final PasswordEncoder passwordEncoder;

  @Override
  @Transactional(readOnly = true)
  public UserResponse findById(UUID id) {
    return userRepository.findById(id).map(mapper::toResponse)
        .orElseThrow(() -> new RuntimeException("User not found."));
  }

  @Transactional
  public UserResponse create(UserCreateRequest request) {
    User user = mapper.toEntity(request);
    String hashedPassword = passwordEncoder.encode(request.password());
    user.setPassword(hashedPassword);
    return mapper.toResponse(userRepository.save(user));
  }

  @Transactional
  public UserResponse update(UUID id, UserUpdateRequest request) {
    User user =
        userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found."));

    mapper.updateEntity(request, user);

    if (request.password() != null && !request.password().isBlank()) {
      String hashedPassword = passwordEncoder.encode(request.password());
      user.setPassword(passwordEncoder.encode(hashedPassword));
    }

    return mapper.toResponse(userRepository.save(user));
  }

  @Transactional(readOnly = true)
  public Page<UserResponse> search(UserSearchFilter filter, Pageable pageable) {
    return userRepository.findAll(UserSpecs.byFilter(filter), pageable).map(mapper::toResponse);
  }

  @Transactional
  public void delete(UUID id) {
    if (!userRepository.existsById(id)) {
      throw new RuntimeException("Invalid ID.");
    }
    userRepository.deleteById(id);
  }
}
