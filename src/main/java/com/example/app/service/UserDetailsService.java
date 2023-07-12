package com.example.app.service;

import com.example.app.model.User;
import com.example.app.model.auth.UserData;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface UserDetailsService {

  User findById(UUID id);

  Set<User> getUserListByIds(List<UUID> userIds);

  User findByEmail(String email);

  UserData map(User user);

  List<UserData> map(Set<User> users);

  Page<UserData> searchByEmail(String email, Pageable pageable);

  Page<UserData> findAllUsers(Pageable pageable);

}
