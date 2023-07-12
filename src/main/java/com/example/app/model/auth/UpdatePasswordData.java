package com.example.app.model.auth;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

@Data
public class UpdatePasswordData {

  @NotBlank
  @Size(min = 6, message = "Password should have at least 6 characters")
  private String oldPassword;

  @NotBlank
  @Size(min = 6, message = "Password should have at least 6 characters")
  private String newPassword;

  @Builder
  public UpdatePasswordData(@NotBlank String oldPassword, @NotBlank String newPassword) {
    this.oldPassword = oldPassword;
    this.newPassword = newPassword;
  }
}