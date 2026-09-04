package br.com.lucascostabueno.vetmanager.api.common.domain.exception;

import org.springframework.http.HttpStatus;

public class ResourceNotFoundException extends BusinessException {
  public ResourceNotFoundException(String message) {
    super(message, HttpStatus.NOT_FOUND);
  }
}
