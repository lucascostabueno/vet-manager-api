package br.com.lucascostabueno.vetmanager.api.common.domain.service;

import br.com.lucascostabueno.vetmanager.api.common.application.mapper.BaseMapper;
import br.com.lucascostabueno.vetmanager.api.common.domain.repository.BaseRepository;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;

public abstract class BaseService<T, ID extends Serializable, RequestDTO, ResponseDTO>
    extends BaseReadService<T, ID, RequestDTO, ResponseDTO> {

  protected BaseService(BaseRepository<T, ID> repository,
      BaseMapper<T, RequestDTO, ResponseDTO> mapper) {
    super(repository, mapper);
  }

  protected void validateCreate(RequestDTO request) {}

  protected void beforeCreate(RequestDTO request, T entity) {}

  protected void afterCreate(T entity, ResponseDTO response) {}

  protected void validateUpdate(ID id, RequestDTO request, T entity) {}

  protected void beforeUpdate(ID id, RequestDTO request, T entity) {}

  protected void afterUpdate(T entity, ResponseDTO response) {}

  protected void validateDelete(ID id, T entity) {}

  protected void beforeDelete(T entity) {}

  protected void afterDelete(ID id) {}

  @Transactional
  public ResponseDTO create(RequestDTO request) {
    validateCreate(request);

    T entity = mapper.toEntity(request);
    beforeCreate(request, entity);

    T savedEntity = repository.save(entity);
    ResponseDTO response = mapper.toResponse(savedEntity);

    afterCreate(savedEntity, response);
    return response;
  }

  @Transactional
  public ResponseDTO update(ID id, RequestDTO request) {
    T entity = findEntityById(id);

    validateUpdate(id, request, entity);
    beforeUpdate(id, request, entity);

    mapper.updateEntity(request, entity);
    T updatedEntity = repository.save(entity);
    ResponseDTO response = mapper.toResponse(updatedEntity);

    afterUpdate(updatedEntity, response);
    return response;
  }

  @Transactional
  public void delete(ID id) {
    T entity = findEntityById(id);

    validateDelete(id, entity);
    beforeDelete(entity);

    repository.delete(entity);
    afterDelete(id);
  }
}
