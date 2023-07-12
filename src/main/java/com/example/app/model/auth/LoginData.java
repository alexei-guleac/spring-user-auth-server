package com.example.app.model.auth;


import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class LoginData {

  private String email;

  @NotBlank
  @Size(min = 6, message = "Password should have at least 6 characters")
  private String password;

  @Builder
  public LoginData(String email, @NotBlank String password) {
    this.email = email;
    this.password = password;
  }
}