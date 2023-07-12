package com.example.app.controller;

import com.example.app.model.User;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

  @GetMapping("/test")
  @ResponseStatus(HttpStatus.OK)
  public String test() {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    return authentication.getName();
  }

  @GetMapping("/user")
  @ResponseStatus(HttpStatus.OK)
  public String loggedUser(@AuthenticationPrincipal User user) {
    return user.getId().toString();
  }
}
