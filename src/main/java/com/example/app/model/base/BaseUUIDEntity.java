package com.example.app.model.base;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.UuidGenerator;


@Getter
@Setter
@ToString
@MappedSuperclass
@NoArgsConstructor
@AllArgsConstructor
public class BaseUUIDEntity implements Serializable {

  @Id
  @UuidGenerator(style = UuidGenerator.Style.TIME)
  private UUID id;

  @Column(name = "create_user_name", nullable = false)
  private String createUserName;

  @Column(name = "update_user_name")
  private String updateUserName;

  @Column(name = "create_datetime", nullable = false)
  @CreationTimestamp
  private Timestamp createDateTime;

  @Column(name = "update_datetime")
  @UpdateTimestamp
  private Timestamp updateDateTime;

  @Column(name = "deleted", nullable = false)
  private boolean deleted;

}
