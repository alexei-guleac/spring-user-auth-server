package com.example.app.model.exception;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class ValidationExceptionData implements Serializable {

  private String errorMessage;

  private Object[] paramKeyValues;

}
