package com.example.app.exception;

import com.example.app.model.exception.ValidationExceptionData;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class ConflictException extends RuntimeException {

  public ValidationExceptionData validationExceptionData;

  public ConflictException(ValidationExceptionData validationExceptionData) {
    super(validationExceptionData.getErrorMessage());
    this.validationExceptionData = validationExceptionData;
  }

  public ConflictException() {
    super();
  }

  public ConflictException(String message, Throwable cause) {
    super(message, cause);
  }

  public ConflictException(String message) {
    super(message);
  }

  public ConflictException(Throwable cause) {
    super(cause);
  }
}
