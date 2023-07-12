package com.example.app.exception.handlers;


import com.example.app.model.exception.ErrorCode;
import com.example.app.model.exception.ExceptionDetails;
import com.example.app.model.exception.ValidationExceptionData;
import java.util.Date;
import java.util.Locale;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.WebRequest;

@Component
public class ExceptionHandlerMapper {

  private final String errorKey = "error.";

  String getContextPath(WebRequest request) {
    return request != null ? request.getContextPath() : StringUtils.EMPTY;
  }

  boolean isCodedErrorMessage(String message) {
    return message.contains(errorKey);
  }

  ExceptionDetails getCustomExceptionDetails(Exception ex,
      String contextPath, HttpStatus statusCode, ErrorCode errorCode,
      ValidationExceptionData validationExceptionData) {

    return ExceptionDetails.builder()
        .timestamp(new Date())
        .code(errorCode)
        .status(statusCode.value())
        .path(contextPath)
        .errorMessage(
            getSpecifiedErrorMessage(ex, validationExceptionData, null))
        .build();

  }

  ExceptionDetails getCustomExceptionDetailsForDefaultExceptions(Exception ex,
      String contextPath, HttpStatus statusCode, ErrorCode errorCode,
      ValidationExceptionData validationExceptionData) {

    return ExceptionDetails.builder()
        .timestamp(new Date())
        .code(errorCode)
        .status(statusCode.value())
        .path(contextPath)
        .errorMessage(validationExceptionData.getErrorMessage())
        .build();

  }

  private String getSpecifiedErrorMessage(Exception ex,
      ValidationExceptionData validationExceptionData, Locale locale) {

    return ex.getMessage() != null && isCodedErrorMessage(ex.getMessage())
        ? ex.getMessage() : ex.getMessage();
  }

  ExceptionDetails getExceptionDetails(Exception ex, ErrorCode errorCode, String contextPath,
      HttpStatus statusCode) {

    return ExceptionDetails.builder()
        .timestamp(new Date())
        .code(errorCode)
        .status(statusCode.value())
        .path(contextPath)
        .errorMessage(ex.getMessage())
        .build();
  }
}
