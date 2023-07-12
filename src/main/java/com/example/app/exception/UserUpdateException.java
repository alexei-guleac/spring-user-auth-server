package com.example.app.exception;

import com.example.app.model.exception.ValidationExceptionData;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class UserUpdateException extends RuntimeException {

  public ValidationExceptionData validationExceptionData;

  public UserUpdateException(ValidationExceptionData validationExceptionData) {
    super(validationExceptionData.getErrorMessage());
    this.validationExceptionData = validationExceptionData;
  }

  public UserUpdateException() {
    super();
  }

  public UserUpdateException(String message, Throwable cause) {
    super(message, cause);
  }

  public UserUpdateException(String message) {
    super(message);
  }

  public UserUpdateException(Throwable cause) {
    super(cause);
  }

}
