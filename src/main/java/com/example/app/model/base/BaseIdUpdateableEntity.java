package com.example.app.model.base;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import java.io.Serializable;
import java.sql.Timestamp;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;


@Getter
@Setter
@ToString
@MappedSuperclass
@NoArgsConstructor
@AllArgsConstructor
public class BaseIdUpdateableEntity implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long id;

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
