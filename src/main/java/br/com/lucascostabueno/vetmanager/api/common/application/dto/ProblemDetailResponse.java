package br.com.lucascostabueno.vetmanager.api.common.application.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;

import java.time.OffsetDateTime;
import java.util.List;

@Getter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProblemDetailResponse {

  private final OffsetDateTime timestamp;
  private final Integer status;
  private final String title;
  private final String detail;
  private final List<FieldError> errors;

  @Getter
  @Builder
  public static class FieldError {
    private final String field;
    private final String message;
  }
}
