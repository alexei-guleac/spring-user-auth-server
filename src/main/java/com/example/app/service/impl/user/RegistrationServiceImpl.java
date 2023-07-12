package com.example.app.service.impl.user;

import com.example.app.config.authorization.TokenGenerator;
import com.example.app.mapper.UserMapper;
import com.example.app.model.User;
import com.example.app.model.auth.TokenInfo;
import com.example.app.model.auth.UserData;
import com.example.app.service.RegistrationService;
import java.util.Collections;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class RegistrationServiceImpl implements RegistrationService {


  private final UserDetailsManager userDetailsManager;

  private final UserMapper userMapper;

  private final TokenGenerator tokenGenerator;


  @Override
  public TokenInfo registerUser(UserData userData) {
    User userDetails = userMapper.map(userData);
    userDetailsManager.createUser(userDetails);

    Authentication authentication = UsernamePasswordAuthenticationToken
        .authenticated(userDetails, userData.getPassword(), Collections.EMPTY_LIST);

    return tokenGenerator.createToken(authentication);
  }
}
