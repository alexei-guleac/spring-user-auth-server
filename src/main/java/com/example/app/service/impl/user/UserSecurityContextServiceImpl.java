package com.example.app.service.impl.user;

import static com.example.app.util.DataUtils.resolve;

import com.example.app.exception.UserNotFoundException;
import com.example.app.mapper.UserMapper;
import com.example.app.model.User;
import com.example.app.model.auth.TokenInfo;
import com.example.app.model.auth.UserData;
import com.example.app.repository.UserRepository;
import com.example.app.service.UserSecurityContextService;
import jakarta.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;


@Slf4j
@Service
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class UserSecurityContextServiceImpl implements UserSecurityContextService {


  @Autowired
  UserRepository userRepository;

  @Autowired
  UserMapper userMapper;

  @Override
  public User findById(UUID id) {
    return userRepository.findById(id)
        .orElseThrow(() -> new UserNotFoundException("Id", id));
  }

  @Override
  public UserData currentUser() {
    User user = getCurrentUser();
    return userMapper.map(user);
  }

  @Override
  public User getCurrentUser() {
    return findById(getCurrentUserId());
  }

  @Override
  public UUID getCurrentUserId() {
    SecurityContext context = SecurityContextHolder.getContext();
    final boolean validUserExistsInContext = resolve(
        () -> context.getAuthentication().getPrincipal()).isPresent();

    if (validUserExistsInContext) {
      Object principal = context.getAuthentication().getPrincipal();
      log.error(String.valueOf(principal));
      if (principal instanceof TokenInfo) {
        return UUID.fromString(((TokenInfo) principal).getUserId());
      }
      return ((User) principal).getId();
    }
    return null;
  }

  @Override
  public String getUserPrincipalInfo() {
    SecurityContext context = SecurityContextHolder.getContext();
    final boolean validUserExistsInContext = resolve(
        () -> context.getAuthentication().getPrincipal()).isPresent();

    if (validUserExistsInContext) {
      Object principal = context.getAuthentication().getPrincipal();
      log.error(String.valueOf(principal));
      if (principal instanceof String) {
        return (String) context.getAuthentication()
            .getPrincipal();
      }
      return ((User) principal).getId().toString();
    }
    return null;
  }

  @Override
  public Map<String, String> getRequestHeaders(HttpServletRequest httpServletRequest) {
    Map<String, String> headers = new HashMap<>();

    Enumeration headerNames = httpServletRequest.getHeaderNames();
    while (headerNames.hasMoreElements()) {
      String key = (String) headerNames.nextElement();
      String value = httpServletRequest.getHeader(key);
      headers.put(key, value);
    }

    return headers;
  }
}
