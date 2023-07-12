package com.example.app.model;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@Table(name = "user_role")
@Entity
@Data
public class UserRole {

  @EmbeddedId
  private UserRoleId userRoleId;
}
