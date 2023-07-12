package com.example.app.service.impl.user;

import com.example.app.exception.UserNotFoundException;
import com.example.app.mapper.UserMapper;
import com.example.app.model.User;
import com.example.app.model.auth.UserData;
import com.example.app.repository.UserRepository;
import com.example.app.service.UserDetailsService;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.internal.util.stereotypes.Lazy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

  @Autowired
  UserRepository userRepository;

  @Autowired
  @Lazy
  PasswordEncoder passwordEncoder;

  @Autowired
  UserMapper userMapper;

  @Autowired
  UserDetailsManager userDetailsManager;


  @Override
  public User findById(UUID id) {
    return userRepository.findById(id)
        .orElseThrow(() -> new UserNotFoundException("Id", id));
  }

  @Override
  public Set<User> getUserListByIds(List<UUID> userIds) {

    Set<User> users = new HashSet<>();
    for (UUID userId : userIds) {
      User user = findById(userId);
      users.add(user);
    }
    return users;
  }


  @Override
  public Page<UserData> searchByEmail(String email, Pageable pageable) {
    Page<User> foundUsersPage = userRepository.findByMailContaining(email, pageable);
    return foundUsersPage.map(this::map);
  }

  @Override
  public User findByEmail(String email) {
    return userRepository.findByMail(email)
        .orElseThrow(() -> new UserNotFoundException("Email", email));
  }

  @Override
  public Page<UserData> findAllUsers(Pageable pageable) {
    Page<User> users = userRepository.findAll(pageable);
    return users.map(userMapper::map);
  }

  @Override
  public UserData map(User user) {
    return userMapper.map(user);
  }

  @Override
  public List<UserData> map(Set<User> users) {
    return users.stream().map(this::map).collect(Collectors.toList());
  }
}
