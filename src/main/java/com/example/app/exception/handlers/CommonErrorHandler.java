package com.example.app.exception.handlers;

import com.example.app.exception.AlreadyExistsException;
import com.example.app.exception.BadRequestException;
import com.example.app.exception.ConflictException;
import com.example.app.exception.ElementNotFoundException;
import com.example.app.model.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
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
public class CommonErrorHandler extends ResponseEntityExceptionHandler {

  private final ExceptionHandlerMapper exceptionHandlerMapper;


  @ExceptionHandler(value = {AlreadyExistsException.class})
  protected ResponseEntity<Object> alreadyExists(
      AlreadyExistsException ex, WebRequest request) {

    return ResponseEntity.status(HttpStatus.FOUND).body(exceptionHandlerMapper
        .getCustomExceptionDetails(ex,
            exceptionHandlerMapper.getContextPath(request), HttpStatus.FOUND,
            ErrorCode.FOUND, ex.getValidationExceptionData()));
  }

  @ExceptionHandler(ElementNotFoundException.class)
  protected ResponseEntity<Object> noElement(
      ElementNotFoundException ex, WebRequest request) {

    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exceptionHandlerMapper
        .getCustomExceptionDetails(ex,
            exceptionHandlerMapper.getContextPath(request), HttpStatus.NOT_FOUND,
            ErrorCode.NOT_FOUND, ex.getValidationExceptionData()));
  }


  @ExceptionHandler(ConflictException.class)
  protected ResponseEntity<Object> managerReservationCancellation(
      ConflictException ex, WebRequest request) {

    return ResponseEntity.status(HttpStatus.FORBIDDEN).body(exceptionHandlerMapper
        .getCustomExceptionDetails(ex,
            exceptionHandlerMapper.getContextPath(request), HttpStatus.FORBIDDEN,
            ErrorCode.FORBIDDEN, ex.getValidationExceptionData()));
  }

  @ExceptionHandler(BadRequestException.class)
  protected ResponseEntity<Object> badRequest(
      BadRequestException ex, WebRequest request) {

    return ResponseEntity.badRequest().body(exceptionHandlerMapper
        .getCustomExceptionDetails(ex,
            exceptionHandlerMapper.getContextPath(request), HttpStatus.BAD_REQUEST,
            ErrorCode.BAD_REQUEST, ex.getValidationExceptionData()));
  }

}
