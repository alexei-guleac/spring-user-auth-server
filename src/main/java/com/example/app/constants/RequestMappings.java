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

  public static final String[] SERVICE_ENDPOINTS = {

      "/swagger-ui.html", "/webjars/**", "/swagger-resources", "/swagger-resources/**",
      "/v2/api-docs", "/configuration/ui", "/configuration/security", "/swagger-ui/**",
      "/v3/api-docs/**",

      "/swagger-ui/**",
      "/swagger-resources/**",
      "/swagger-ui.html",
      "/v2/api-docs/**",
      "/v3/api-docs/**",
      "/h2-console/**",
      "/actuator/**"};
}
