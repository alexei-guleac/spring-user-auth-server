package com.example.app.controller;


import static com.example.app.constants.RequestMappings.USERS_ROOT;

import com.example.app.controller.api.UserDataApi;
import com.example.app.mapper.UserMapper;
import com.example.app.model.User;
import com.example.app.model.auth.UserData;
import com.example.app.service.UserDetailsService;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping(USERS_ROOT)
public class UserDataController implements UserDataApi {

  private final UserDetailsService userDetailsService;

  private final UserMapper userMapper;

  @Override
  public UserData user(User user, UUID id) {
    log.info("user " + user);
    return userMapper.map(userDetailsService.findById(id));
  }

  @Override
  public Set<UserData> getUserListByIds(List<UUID> userIds) {
    return userDetailsService.getUserListByIds(userIds).stream()
        .map(userMapper::map)
        .collect(Collectors.toSet());
  }

  @Override
  public Set<UserData> getAllUsers(int pageNumber, int pageSize) {
    return userDetailsService.findAllUsers(
        PageRequest.of(pageNumber, pageSize)).toSet();
  }

  @Override
  public List<UserData> searchByEmail(String email, int pageNumber, int pageSize) {
    return userDetailsService.searchByEmail(email,
        PageRequest.of(pageNumber, pageSize)).get().toList();
  }

  @Override
  public UserData getByEmail(String email) {
    return userMapper.map(userDetailsService.findByEmail(email));
  }
}