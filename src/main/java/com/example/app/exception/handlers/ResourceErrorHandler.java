package com.example.app.exception.handlers;

import com.example.app.exception.ConflictException;
import com.example.app.exception.ResourceNotFoundException;
import com.example.app.model.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
@Slf4j
public class ResourceErrorHandler extends ResponseEntityExceptionHandler {

  private final ExceptionHandlerMapper exceptionHandlerMapper;

  @ExceptionHandler(ResourceNotFoundException.class)
  protected ResponseEntity<Object> noElement(
      ResourceNotFoundException ex, WebRequest request) {

    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exceptionHandlerMapper
        .getCustomExceptionDetails(ex,
            exceptionHandlerMapper.getContextPath(request), HttpStatus.NOT_FOUND,
            ErrorCode.NOT_FOUND, ex
                .getValidationExceptionData()));
  }

  @ExceptionHandler(ConflictException.class)
  protected ResponseEntity<Object> conflictInData(
      ConflictException ex, WebRequest request) {

    return ResponseEntity.status(HttpStatus.CONFLICT).body(exceptionHandlerMapper
        .getCustomExceptionDetails(ex,
            exceptionHandlerMapper.getContextPath(request), HttpStatus.CONFLICT,
            ErrorCode.CONFLICT, ex
                .getValidationExceptionData()));
  }
}
