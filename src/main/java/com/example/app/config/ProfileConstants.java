package com.example.app.config;

public class ProfileConstants {

  // Spring profiles for development, test and production
  /**
   * Constant <code>SPRING_PROFILE_DEVELOPMENT="dev"</code>
   */
  public static final String SPRING_PROFILE_DEVELOPMENT = "dev";

  /**
   * Constant <code>SPRING_PROFILE_TEST="test"</code>
   */
  public static final String SPRING_PROFILE_TEST = "test";

  /**
   * Constant <code>SPRING_PROFILE_PRODUCTION="prod"</code>
   */
  public static final String SPRING_PROFILE_PRODUCTION = "prod";

  /**
   * Spring profile used when deploying with Spring Cloud (used when deploying to CloudFoundry)
   * Constant <code>SPRING_PROFILE_CLOUD="cloud"</code>
   */
  public static final String SPRING_PROFILE_CLOUD = "cloud";

  /**
   * Spring profile used to enable swagger Constant <code>SPRING_PROFILE_SWAGGER="swagger"</code>
   */
  public static final String SPRING_PROFILE_SWAGGER = "swagger";

  /**
   * Spring profile used to disable running liquibase Constant <code>SPRING_PROFILE_NO_LIQUIBASE="no-liquibase"</code>
   */
  public static final String SPRING_PROFILE_NO_LIQUIBASE = "no-liquibase";

  public static final String SPRING_PROFILE_TEST_LOCAL = "local";
}
