package com.example.app.model.auth;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class LoginData {

  private String email;
  private String password;

  @Builder
  public LoginData(String email, String password) {
    this.email = email;
    this.password = password;
  }
}