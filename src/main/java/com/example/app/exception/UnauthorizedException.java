package com.example.app.exception;

public class UnauthorizedException extends Exception {

  public UnauthorizedException(String message) {
    super(message);
  }

  public UnauthorizedException(String message, Throwable exception) {
    super(message, exception);
  }
}
