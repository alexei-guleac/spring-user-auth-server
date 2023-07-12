package com.example.app.model;

import com.example.app.model.base.BaseIdEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import java.sql.Timestamp;
import java.util.UUID;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Entity
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Table(name = "password_history")
public class PasswordHistory extends BaseIdEntity {

  @Column(name = "user_id")
  private UUID userId;

  @NonNull
  @Column(name = "password")
  private String password;

  @Builder
  public PasswordHistory(Long id, String createUserName, Timestamp createDateTime,
      UUID userId, @NonNull String password) {
    super(id, createUserName, createDateTime);
    this.userId = userId;
    this.password = password;
  }
}
