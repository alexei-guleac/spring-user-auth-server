package com.example.app.model.enums;


import java.util.Arrays;

public enum ErrorCodeEnum {
  //HTTP status 404
  RESOURCE_NOT_FOUND("error.resource.not.found", "Resource with this id not found : {0}");


  private final String errorKey;

  private final String errorMsg;

  ErrorCodeEnum(String errorKey, String errorMsg) {
    this.errorKey = errorKey;
    this.errorMsg = errorMsg;
  }

  ErrorCodeEnum(String errorKey) {
    this.errorKey = errorKey;
    this.errorMsg = "errorMsg";
  }

  public static ErrorCodeEnum fromCode(String errorKey) {
    for (ErrorCodeEnum type : values()) {
      if (type.errorKey.equalsIgnoreCase(errorKey)) {
        return type;
      }
    }
    throw new IllegalArgumentException(
        "Unknown enum type " + errorKey + ", Allowed values are " + Arrays.toString(values()));
  }

  public String getErrorKey() {
    return errorKey;
  }

  public String getErrorMsg() {
    return errorMsg;
  }

  @Override
  public String toString() {
    return "ErrorCodeEnum{" +
        "errorKey='" + errorKey + '\'' +
        '}';
  }
}
