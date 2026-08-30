package br.com.lucascostabueno.vetmanager.api.common.application.dto;

import java.io.Serializable;

public interface IdentifiableDTO<ID extends Serializable> {
  ID id();
}
