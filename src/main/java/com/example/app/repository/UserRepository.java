package com.example.app.repository;


import com.example.app.model.User;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, UUID>,
    PagingAndSortingRepository<User, UUID> {

  Optional<User> findByMail(String email);

  Page<User> findByMailContaining(String email, Pageable pageable);

  boolean existsByMail(String email);

  @Modifying(clearAutomatically = true, flushAutomatically = true)
  @Query(" UPDATE User u "
      + "     SET u.password  = :newPassword"
      + "   WHERE u.id = :userId"
  )
  void updateUserPassword(UUID userId, String newPassword);
}