package com.example.app.model.exception;

import java.util.Date;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@RequiredArgsConstructor
@Builder
public class ExceptionDetails {

  private final Date timestamp;

  private final String errorMessage;

  private final ErrorCode code;

  private final Integer status;

  private final String path;

}
