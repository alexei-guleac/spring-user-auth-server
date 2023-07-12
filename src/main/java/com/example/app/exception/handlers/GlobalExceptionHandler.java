package com.example.app.exception.handlers;

import static org.apache.commons.lang3.StringUtils.isNotBlank;

import com.example.app.model.exception.ErrorCode;
import com.example.app.model.exception.ExceptionDetails;
import com.fasterxml.jackson.core.JsonParseException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.NestedExceptionUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;


@Slf4j
@ControllerAdvice
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
class GlobalExceptionHandler {

  private final ExceptionHandlerMapper exceptionHandlerMapper;


  @ExceptionHandler({DataAccessException.class})
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  protected ResponseEntity<Object> databaseException(
      DataAccessException ex, WebRequest request) {

    String message = ex.getMessage();
    String bodyOfResponse = isNotBlank(message) ? message
        : request.getDescription(false);
    bodyOfResponse = "Database error: " + bodyOfResponse;

    Exception nestedEx = (Exception) NestedExceptionUtils.getMostSpecificCause(ex);
    Exception exception =
        isNotBlank(nestedEx.getMessage()) ? nestedEx : ex;

    return ResponseEntity.badRequest().body(exceptionHandlerMapper
        .getCustomExceptionDetails(exception,
            exceptionHandlerMapper.getContextPath(request), HttpStatus.BAD_REQUEST,
            ErrorCode.BAD_REQUEST, null));
  }

  @ExceptionHandler(DataIntegrityViolationException.class)
  @ResponseStatus(HttpStatus.CONFLICT)
  protected ResponseEntity<Object> dataIntegrityViolationException(
      DataIntegrityViolationException ex, WebRequest request) {

    String message = ex.getMessage();
    String bodyOfResponse = isNotBlank(message) ? message
        : request.getDescription(false);
    bodyOfResponse = "Database error: " + bodyOfResponse;

    Exception nestedEx = (Exception) NestedExceptionUtils.getMostSpecificCause(ex);
    Exception exception =
        isNotBlank(nestedEx.getMessage()) ? nestedEx : ex;

    return ResponseEntity.status(HttpStatus.CONFLICT).body(exceptionHandlerMapper
        .getCustomExceptionDetails(exception,
            exceptionHandlerMapper.getContextPath(request), HttpStatus.CONFLICT,
            ErrorCode.CONFLICT, null));
  }

  /**
   * Method that check against {@code @Valid} Objects passed to controller endpoints
   *
   * @param ex
   * @return a {@code ResponseEntity<Object>}
   * @see ResponseEntity < Object >
   */
  @ExceptionHandler(value = {MethodArgumentNotValidException.class,
      MissingPathVariableException.class})
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ResponseEntity<Object> handleMethodArgumentException(MethodArgumentNotValidException ex,
      WebRequest request) {

    return ResponseEntity.badRequest().body(exceptionHandlerMapper
        .getCustomExceptionDetails(ex,
            exceptionHandlerMapper.getContextPath(request), HttpStatus.BAD_REQUEST,
            ErrorCode.BAD_REQUEST, null));
  }

  /**
   * Handle unprocessable json data exception
   *
   * @param ex
   * @return a {@code ExceptionDetails}
   */
  @ExceptionHandler(HttpMessageNotReadableException.class)
  public final ResponseEntity<ExceptionDetails> handleNRException(
      HttpMessageNotReadableException ex, WebRequest request) {

    return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(exceptionHandlerMapper
        .getCustomExceptionDetails(ex,
            exceptionHandlerMapper.getContextPath(request), HttpStatus.UNPROCESSABLE_ENTITY,
            ErrorCode.UNPROCESSABLE_ENTITY, null));
  }

  /**
   * Handle unprocessable json data exception
   *
   * @param ex
   * @return a {@code ExceptionDetails}
   */
  @ExceptionHandler(JsonParseException.class)
  public final ResponseEntity<ExceptionDetails> handleOtherRuntimeException(
      JsonParseException ex, WebRequest request) {

    return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(exceptionHandlerMapper
        .getCustomExceptionDetails(ex,
            exceptionHandlerMapper.getContextPath(request), HttpStatus.UNPROCESSABLE_ENTITY,
            ErrorCode.UNPROCESSABLE_ENTITY, null));
  }
}