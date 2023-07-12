package com.example.app.controller;

import com.example.app.model.User;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {


  @Operation(summary = "Get context for test purposes")
  @GetMapping("/test")
  @ResponseStatus(HttpStatus.OK)
  public String test() {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    return authentication.getName();
  }

  @Operation(summary = "Get current user id for test purposes")
  @GetMapping("/user")
  @ResponseStatus(HttpStatus.OK)
  public String loggedUser(@AuthenticationPrincipal User user) {
    return user.getId().toString();
  }
}
