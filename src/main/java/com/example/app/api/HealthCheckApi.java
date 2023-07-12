package com.example.app.api;


import static com.example.app.constants.ApiDescriptionConstants.API_RESPONSE_INTERNAL_SERVER_ERROR;
import static com.example.app.constants.ApiDescriptionConstants.API_RESPONSE_OK;
import static com.example.app.constants.ApiDescriptionConstants.API_RESPONSE_UNAVAILABLE;
import static com.example.app.constants.ApiDescriptionConstants.INTERNAL_SERVER_ERROR;
import static com.example.app.constants.ApiDescriptionConstants.OK;
import static com.example.app.constants.ApiDescriptionConstants.UNAVAILABLE;

import com.example.app.model.data.healthcheck.HealthCheckResponseData;
import com.example.app.model.exception.ExceptionDetails;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

@Api(value = "HealthCheckApi", description = "The Health check API")
public interface HealthCheckApi {

  @ApiOperation(value = "Check APP health status", nickname = "healthCheck",
      response = HealthCheckResponseData.class, tags = {})
  @ApiResponses(value = {
      @ApiResponse(code = OK, message = API_RESPONSE_OK),
      @ApiResponse(code = INTERNAL_SERVER_ERROR, message = API_RESPONSE_INTERNAL_SERVER_ERROR, response = ExceptionDetails.class),
      @ApiResponse(code = UNAVAILABLE, message = API_RESPONSE_UNAVAILABLE)})
  @GetMapping
  ResponseEntity<HealthCheckResponseData> healthCheck();

}
