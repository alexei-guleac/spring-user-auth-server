package com.example.app.model.auth;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserData {

  @JsonProperty(value = "id", access = JsonProperty.Access.READ_ONLY)
  @Schema(accessMode = Schema.AccessMode.READ_ONLY)
  private String id;

  @JsonProperty("username")
  private String username;

  @JsonProperty("mail")
  @NotBlank
  @Email
  private String mail;

  @JsonProperty(value = "password", access = JsonProperty.Access.WRITE_ONLY)
  @Schema(accessMode = Schema.AccessMode.WRITE_ONLY)
  @NotBlank
  @ToString.Exclude
  private String password;

  @JsonProperty("firstName")
  @NotBlank
  @Size(min = 2, message = "First name should have at least 2 characters")
  private String firstName;

  @JsonProperty("lastName")
  @NotBlank
  @Size(min = 2, message = "Last name should have at least 2 characters")
  private String lastName;

  @JsonProperty("middleName")
  private String middleName;

  @JsonProperty("birthday")
  private LocalDate birthday;

  @Builder
  public UserData(String id, String username,
      @NotBlank @Email String mail,
      @NotBlank String password,
      @NotBlank String firstName,
      @NotBlank String lastName,
      String middleName, LocalDate birthday) {
    this.id = id;
    this.username = username;
    this.mail = mail;
    this.password = password;
    this.firstName = firstName;
    this.lastName = lastName;
    this.middleName = middleName;
    this.birthday = birthday;
  }
}