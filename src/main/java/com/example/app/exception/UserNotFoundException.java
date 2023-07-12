package com.example.app.exception;

import com.example.app.model.exception.ValidationExceptionData;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class UserNotFoundException extends RuntimeException {

  public ValidationExceptionData validationExceptionData;

  public UserNotFoundException(ValidationExceptionData validationExceptionData) {
    super(validationExceptionData.getErrorMessage());
    this.validationExceptionData = validationExceptionData;
  }

  public UserNotFoundException() {
    super();
  }

  public UserNotFoundException(String field, Object value) {
    super(String.format("User was not found with %s : %s", field, value.toString()));
    this.validationExceptionData = ValidationExceptionData.builder()
        .errorMessage(String.format("User was not found with %s : %s", field, value.toString()))
        .paramKeyValues(new Object[]{value}).build();
  }

  public UserNotFoundException(String message, Throwable cause) {
    super(message, cause);
  }

  public UserNotFoundException(String message) {
    super(message);
  }

  public UserNotFoundException(Throwable cause) {
    super(cause);
  }

}
