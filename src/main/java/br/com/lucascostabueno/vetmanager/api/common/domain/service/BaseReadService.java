package br.com.lucascostabueno.vetmanager.api.common.domain.service;

import br.com.lucascostabueno.vetmanager.api.common.application.mapper.BaseMapper;
import br.com.lucascostabueno.vetmanager.api.common.domain.repository.BaseRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;

public abstract class BaseReadService<T, ID extends Serializable, RequestDTO, ResponseDTO> {

  protected final BaseRepository<T, ID> repository;
  protected final BaseMapper<T, RequestDTO, ResponseDTO> mapper;

  protected BaseReadService(BaseRepository<T, ID> repository,
      BaseMapper<T, RequestDTO, ResponseDTO> mapper) {
    this.repository = repository;
    this.mapper = mapper;
  }

  protected Specification<T> buildSpecification(RequestDTO filter) {
    return (root, query, cb) -> cb.conjunction();
  }

  public T findEntityById(ID id) {
    return repository.findById(id)
        .orElseThrow(() -> new RuntimeException("Registro não encontrado para o ID: " + id));
  }

  protected List<T> searchEntities(RequestDTO filter) {
    Specification<T> spec = buildSpecification(filter);
    return repository.findAll(spec);
  }

  protected Page<T> searchPagedEntities(RequestDTO filter, Pageable pageable) {
    Specification<T> spec = buildSpecification(filter);
    return repository.findAll(spec, pageable);
  }

  @Transactional(readOnly = true)
  public ResponseDTO findById(ID id) {
    T entity = findEntityById(id);
    return mapper.toResponse(entity);
  }

  @Transactional(readOnly = true)
  public List<ResponseDTO> search(RequestDTO filter) {
    return searchEntities(filter).stream().map(mapper::toResponse).toList();
  }

  @Transactional(readOnly = true)
  public Page<ResponseDTO> searchPaged(RequestDTO filter, Pageable pageable) {
    return searchPagedEntities(filter, pageable).map(mapper::toResponse);
  }
}
