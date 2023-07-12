package com.example.app.exception;

import com.example.app.model.exception.ValidationExceptionData;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class PasswordUpdateException extends RuntimeException {

  public ValidationExceptionData validationExceptionData;

  public PasswordUpdateException(ValidationExceptionData validationExceptionData) {
    super(validationExceptionData.getErrorMessage());
    this.validationExceptionData = validationExceptionData;
  }

  public PasswordUpdateException() {
    super();
  }

  public PasswordUpdateException(String message, Throwable cause) {
    super(message, cause);
  }

  public PasswordUpdateException(String message) {
    super(message);
  }

  public PasswordUpdateException(Throwable cause) {
    super(cause);
  }

}
