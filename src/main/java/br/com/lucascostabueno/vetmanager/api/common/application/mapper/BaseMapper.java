package br.com.lucascostabueno.vetmanager.api.common.application.mapper;

import org.mapstruct.MappingTarget;

public interface BaseMapper<T, RequestDTO, ResponseDTO> {
  T toEntity(RequestDTO request);

  ResponseDTO toResponse(T entity);

  void updateEntity(RequestDTO request, @MappingTarget T entity);
}
