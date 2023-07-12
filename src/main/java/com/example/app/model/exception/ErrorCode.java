package com.example.app.model.exception;

import java.util.Arrays;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;

public enum ErrorCode {

  /**
   * Internal server error. Typically a server bug.
   */
  SOFTWARE_ERROR(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value())),

  EXTERNAL_API_ERROR(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value())),

  INVALID_LICENSE(String.valueOf(HttpStatus.NOT_ACCEPTABLE.value())),

  UNAUTHORIZED(String.valueOf(HttpStatus.UNAUTHORIZED.value())),

  FORBIDDEN(String.valueOf(HttpStatus.FORBIDDEN.value())),

  EXPIRED_LICENSE(String.valueOf(HttpStatus.PAYMENT_REQUIRED.value())),

  NO_CONTENT(String.valueOf(HttpStatus.NO_CONTENT.value())),

  BAD_REQUEST(String.valueOf(HttpStatus.BAD_REQUEST.value())),

  UNPROCESSABLE_ENTITY(String.valueOf(HttpStatus.UNPROCESSABLE_ENTITY.value())),

  FOUND(String.valueOf(HttpStatus.FOUND.value())),

  /**
   * Client specified an invalid argument.
   */
  INVALID_ARGUMENT(String.valueOf(HttpStatus.NOT_ACCEPTABLE.value())),

  /**
   * Request cannot be executed in the current system state, such as deleting a non-empty
   * directory.
   */
  FAILED_PRECONDITION(StringUtils.EMPTY),

  /**
   * Client specified an invalid range.
   */
  OUT_OF_RANGE(StringUtils.EMPTY),

  /**
   * Request not authenticated due to missing(StringUtils.EMPTY) invalid, or expired OAuth token.
   */
  UNAUTHENTICATED(String.valueOf(HttpStatus.UNAUTHORIZED.value())),

  /**
   * Client does not have sufficient permission. This can happen because the OAuth token does not
   * have the right scopes, the client doesn't have permission, or the API has not been enabled for
   * the client project.
   */
  PERMISSION_DENIED(String.valueOf(HttpStatus.FORBIDDEN.value())),

  /**
   * A specified resource is not found, or the request is rejected for unknown reasons, such as a
   * blocked network address.
   */
  NOT_FOUND(String.valueOf(HttpStatus.NOT_FOUND.value())),

  /**
   * Concurrency conflict, such as read-modify-write conflict.
   */
  CONFLICT(String.valueOf(HttpStatus.CONFLICT.value())),

  /**
   * Concurrency conflict, such as read-modify-write conflict.
   */
  ABORTED(StringUtils.EMPTY),

  /**
   * The resource that a client tried to create already exists.
   */
  ALREADY_EXISTS(StringUtils.EMPTY),

  /**
   * Either out of resource quota or rate limited.
   */
  RESOURCE_EXHAUSTED(StringUtils.EMPTY),

  /**
   * Request cancelled by the client.
   */
  CANCELLED(StringUtils.EMPTY),

  /**
   * Unrecoverable data loss or data corruption. The client should report the error to the user.
   */
  DATA_LOSS(StringUtils.EMPTY),

  /**
   * Unknown server error. Typically a server bug.
   */
  UNKNOWN(StringUtils.EMPTY),

  /**
   * Service unavailable. Typically the server is down.
   */
  UNAVAILABLE(String.valueOf(HttpStatus.SERVICE_UNAVAILABLE.value()));

  private final String value;

  ErrorCode(String value) {
    this.value = value;
  }

  public static ErrorCode fromValue(String value) {
    for (ErrorCode type : values()) {
      if (type.value.equalsIgnoreCase(value)) {
        return type;
      }
    }
    throw new IllegalArgumentException(
        "Unknown enum type " + value + ", Allowed values are " + Arrays.toString(values()));
  }

  public Integer getValue() {
    return Integer.parseInt(this.value);
  }
}
