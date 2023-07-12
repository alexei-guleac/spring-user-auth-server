package com.example.app.controller.api;

import static com.example.app.constants.ApiDescriptionConstants.API_RESPONSE_BAD_REQUEST;
import static com.example.app.constants.ApiDescriptionConstants.API_RESPONSE_FORBIDDEN;
import static com.example.app.constants.ApiDescriptionConstants.API_RESPONSE_INTERNAL_SERVER_ERROR;
import static com.example.app.constants.ApiDescriptionConstants.API_RESPONSE_NOT_FOUND;
import static com.example.app.constants.ApiDescriptionConstants.API_RESPONSE_OK;
import static com.example.app.constants.ApiDescriptionConstants.API_RESPONSE_UNAUTHORIZED;
import static com.example.app.constants.ApiDescriptionConstants.API_RESPONSE_UNAVAILABLE;
import static com.example.app.constants.ApiDescriptionConstants.API_RESPONSE_UNPROCESSABLE_ENTITY;
import static com.example.app.constants.ApiDescriptionConstants.BAD_REQUEST;
import static com.example.app.constants.ApiDescriptionConstants.FORBIDDEN;
import static com.example.app.constants.ApiDescriptionConstants.INTERNAL_SERVER_ERROR;
import static com.example.app.constants.ApiDescriptionConstants.NOT_FOUND;
import static com.example.app.constants.ApiDescriptionConstants.OK;
import static com.example.app.constants.ApiDescriptionConstants.UNAUTHORIZED;
import static com.example.app.constants.ApiDescriptionConstants.UNAVAILABLE;
import static com.example.app.constants.ApiDescriptionConstants.UNPROCESSABLE_ENTITY;

import com.example.app.model.auth.LoginData;
import com.example.app.model.auth.TokenInfo;
import com.example.app.model.exception.ExceptionDetails;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;


@Api(value = "Auth", tags = {
    "User - authentication controller",})
public interface AuthenticationApi {

  @ApiOperation(value = "Login user and get authentication token",
      nickname = "loginUser", tags = {})
  @ApiResponses(value = {
      @ApiResponse(code = OK, message = API_RESPONSE_OK, response = TokenInfo.class),
      @ApiResponse(code = BAD_REQUEST, message = API_RESPONSE_BAD_REQUEST, response = ExceptionDetails.class),
      @ApiResponse(code = UNAUTHORIZED, message = API_RESPONSE_UNAUTHORIZED),
      @ApiResponse(code = FORBIDDEN, message = API_RESPONSE_FORBIDDEN),
      @ApiResponse(code = NOT_FOUND, message = API_RESPONSE_NOT_FOUND),
      @ApiResponse(code = UNPROCESSABLE_ENTITY, message = API_RESPONSE_UNPROCESSABLE_ENTITY),
      @ApiResponse(code = INTERNAL_SERVER_ERROR, message = API_RESPONSE_INTERNAL_SERVER_ERROR),
      @ApiResponse(code = UNAVAILABLE, message = API_RESPONSE_UNAVAILABLE)})
  @PostMapping("/login")
  @ResponseStatus(HttpStatus.OK)
  TokenInfo loginUser(@Valid @RequestBody LoginData loginData);

  @ApiOperation(value = "Logout current user context",
      nickname = "logout", tags = {})
  @ApiResponses(value = {
      @ApiResponse(code = OK, message = API_RESPONSE_OK),
      @ApiResponse(code = BAD_REQUEST, message = API_RESPONSE_BAD_REQUEST, response = ExceptionDetails.class),
      @ApiResponse(code = UNAUTHORIZED, message = API_RESPONSE_UNAUTHORIZED),
      @ApiResponse(code = FORBIDDEN, message = API_RESPONSE_FORBIDDEN),
      @ApiResponse(code = NOT_FOUND, message = API_RESPONSE_NOT_FOUND),
      @ApiResponse(code = UNPROCESSABLE_ENTITY, message = API_RESPONSE_UNPROCESSABLE_ENTITY),
      @ApiResponse(code = INTERNAL_SERVER_ERROR, message = API_RESPONSE_INTERNAL_SERVER_ERROR),
      @ApiResponse(code = UNAVAILABLE, message = API_RESPONSE_UNAVAILABLE)})
  @PostMapping("/logout")
  @ResponseStatus(HttpStatus.OK)
  void logout();

  @ApiOperation(value = "Get authentication refresh token",
      nickname = "getToken", tags = {})
  @ApiResponses(value = {
      @ApiResponse(code = OK, message = API_RESPONSE_OK, response = TokenInfo.class),
      @ApiResponse(code = BAD_REQUEST, message = API_RESPONSE_BAD_REQUEST, response = ExceptionDetails.class),
      @ApiResponse(code = UNAUTHORIZED, message = API_RESPONSE_UNAUTHORIZED),
      @ApiResponse(code = FORBIDDEN, message = API_RESPONSE_FORBIDDEN),
      @ApiResponse(code = NOT_FOUND, message = API_RESPONSE_NOT_FOUND),
      @ApiResponse(code = UNPROCESSABLE_ENTITY, message = API_RESPONSE_UNPROCESSABLE_ENTITY),
      @ApiResponse(code = INTERNAL_SERVER_ERROR, message = API_RESPONSE_INTERNAL_SERVER_ERROR),
      @ApiResponse(code = UNAVAILABLE, message = API_RESPONSE_UNAVAILABLE)})
  @PostMapping("/token")
  @ResponseStatus(HttpStatus.OK)
  TokenInfo getToken(@Valid @RequestBody TokenInfo tokenInfo);
}