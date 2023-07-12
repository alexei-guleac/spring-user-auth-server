package com.example.app.integration;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;
import static org.springframework.http.HttpStatus.CREATED;

import com.example.app.Application;
import com.example.app.AuthenticationHelper;
import com.example.app.config.TestDataSourceConfiguration;
import com.example.app.integration.container.PostgresTestContainer;
import com.example.app.model.auth.UserData;
import java.util.HashMap;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@SuppressWarnings("unchecked")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest(webEnvironment = RANDOM_PORT, classes = {Application.class,
    TestDataSourceConfiguration.class,})
class UserControllerTest extends PostgresTestContainer {

  @Autowired
  private TestRestTemplate template;

  @Autowired
  private AuthenticationHelper authenticationHelper;

  @Test
  void createUser() {
    var map = new HashMap<>();
    map.put("mail", "admin@example.com");
    map.put("password", "admin@example.com");
    var token = authenticationHelper.authenticate("admin@example.com", "admin@example.com");
    var httpEntity = authenticationHelper.generateHeaders(map, token);
    var response = template.exchange("auth/login", HttpMethod.POST, httpEntity, UserData.class);
    assertThat(response.getStatusCode()).isEqualTo(CREATED);
  }
//
//    @Test
//    void getAllUsers() {
//        var map = new HashMap<>();
//        map.put("surname", "test");
//        map.put("forename", "test");
//        map.put("email", "test@4amdaris.com");
//        map.put("password", "amdaris");
//        map.put("userRole", Map.of("name", "MANAGER"));
//        var token = authenticationHelper.authenticate("admin@amdaris.com", "amdaris");
//        var httpEntity = authenticationHelper.generateHeaders(map, token);
//        template.exchange(UserEndpoints.USER_BASE_URL, HttpMethod.POST, httpEntity, UserDto.class);
//        var response = template.exchange(UserEndpoints.USER_BASE_URL, HttpMethod.GET, httpEntity, Object.class);
//        assertThat(response.getStatusCode()).isEqualTo(OK);
//    }
//
//    @Test
//    void getCurrentUser() {
//        var token = authenticationHelper.authenticate("admin@amdaris.com", "amdaris");
//        var httpEntity = authenticationHelper.generateHeaders(token);
//        template.exchange(UserEndpoints.USER_BASE_URL, HttpMethod.POST, httpEntity, UserDto.class);
//        var response = template.exchange(UserEndpoints.USER_BASE_URL + "/current" + CommonEndpoints.PROJECT_ID, HttpMethod.GET, httpEntity, Object.class, 1);
//        assertThat(response.getStatusCode()).isEqualTo(OK);
//    }
//
//    @Test
//    void testGetCurrentUser() {
//        var token = authenticationHelper.authenticate("admin@amdaris.com", "amdaris");
//        var httpEntity = authenticationHelper.generateHeaders(token);
//        template.exchange(UserEndpoints.USER_BASE_URL, HttpMethod.POST, httpEntity, UserDto.class);
//        var response = template.exchange(UserEndpoints.USER_BASE_URL + "/current", HttpMethod.GET, httpEntity, Object.class);
//        assertThat(response.getStatusCode()).isEqualTo(OK);
//    }
}