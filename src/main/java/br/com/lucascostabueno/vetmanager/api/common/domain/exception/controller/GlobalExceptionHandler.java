package br.com.lucascostabueno.vetmanager.api.common.domain.exception.controller;

import br.com.lucascostabueno.vetmanager.api.common.application.dto.ProblemDetailResponse;
import br.com.lucascostabueno.vetmanager.api.common.domain.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.OffsetDateTime;
import java.util.List;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(BusinessException.class)
  public ResponseEntity<ProblemDetailResponse> handleBusinessException(BusinessException ex) {
    log.warn("Business rule violation: {}", ex.getMessage());

    ProblemDetailResponse problem = ProblemDetailResponse.builder().timestamp(OffsetDateTime.now())
        .status(ex.getStatus().value()).title(ex.getStatus().getReasonPhrase())
        .detail(ex.getMessage()).build();

    return ResponseEntity.status(ex.getStatus()).body(problem);
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<ProblemDetailResponse> handleValidationException(
      MethodArgumentNotValidException ex) {
    List<ProblemDetailResponse.FieldError> fieldErrors = ex.getBindingResult().getFieldErrors()
        .stream().map(error -> ProblemDetailResponse.FieldError.builder().field(error.getField())
            .message(error.getDefaultMessage()).build())
        .toList();

    ProblemDetailResponse problem = ProblemDetailResponse.builder().timestamp(OffsetDateTime.now())
        .status(HttpStatus.BAD_REQUEST.value()).title(HttpStatus.BAD_REQUEST.getReasonPhrase())
        .detail("One or more fields are invalid. Please correct them and try again.")
        .errors(fieldErrors).build();

    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(problem);
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<ProblemDetailResponse> handleUncaughtException(Exception ex) {
    log.error("Unhandled internal server error occurred", ex);

    ProblemDetailResponse problem = ProblemDetailResponse.builder().timestamp(OffsetDateTime.now())
        .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
        .title(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase())
        .detail("An unexpected internal server error occurred. Please contact support.").build();

    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(problem);
  }
}
