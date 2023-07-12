package com.example.app.constants;

public class RequestMappings {

  // user auth
  public static final String AUTH_ROOT = "/auth";
  public static final String USERS_ROOT = "/users";


  // mapping constants for ...
  public static final String SOME_ROOT = "/something";

  // health check constants for health-check api
  public static final String HEALTH_CHECK_ROOT = "/status";

  public static final String ALL_MATCHING = "/**";

  public static final String[] SERVICE_ENDPOINTS = {"/swagger-ui/**",
      "/v3/api-docs/**",
      "/h2-console/**",
      "/actuator/**"};
}
