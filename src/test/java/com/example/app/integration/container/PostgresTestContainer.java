package com.example.app.integration.container;

import org.junit.ClassRule;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.testcontainers.containers.PostgreSQLContainer;


@ExtendWith(SpringExtension.class)
@ContextConfiguration(initializers = {
    PostgresTestContainer.Initializer.class})
public class PostgresTestContainer {

  @ClassRule
  public static PostgreSQLContainer postgreSQLContainer = new PostgreSQLContainer("postgres:15.2")
      .withDatabaseName("integration-tests-db")
      .withUsername("sa")
      .withPassword("sa");

  static class Initializer implements
      ApplicationContextInitializer<ConfigurableApplicationContext> {

    public void initialize(ConfigurableApplicationContext configurableApplicationContext) {
      postgreSQLContainer.start();
      TestPropertyValues.of(
          "spring.datasource.url=" + postgreSQLContainer.getJdbcUrl(),
          "spring.datasource.username=" + postgreSQLContainer.getUsername(),
          "spring.datasource.password=" + postgreSQLContainer.getPassword(),
          "spring.jpa.hibernate.ddl-auto=create-drop"
      ).applyTo(configurableApplicationContext.getEnvironment());
    }

  }

}
