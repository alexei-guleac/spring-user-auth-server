package com.example.app.service.impl.user;

import com.example.app.config.authorization.TokenGenerator;
import com.example.app.model.auth.LoginData;
import com.example.app.model.auth.TokenInfo;
import com.example.app.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.BearerTokenAuthenticationToken;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationProvider;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class AuthenticationServiceImpl implements AuthenticationService {

  private final TokenGenerator tokenGenerator;

  private final DaoAuthenticationProvider daoAuthenticationProvider;

  @Qualifier("jwtRefreshTokenAuthProvider")
  private final JwtAuthenticationProvider refreshTokenAuthProvider;


  @Override
  public TokenInfo loginUser(LoginData loginData) {
    Authentication authentication = daoAuthenticationProvider.authenticate(
        UsernamePasswordAuthenticationToken
            .unauthenticated(loginData.getEmail(), loginData.getPassword()));

    return tokenGenerator.createToken(authentication);
  }

  @Override
  public TokenInfo getToken(TokenInfo tokenInfo) {
    Authentication authentication = refreshTokenAuthProvider
        .authenticate(new BearerTokenAuthenticationToken(tokenInfo.getRefreshToken()));
    Jwt jwt = (Jwt) authentication.getCredentials();
    // check if present in db and not revoked, etc

    return tokenGenerator.createToken(authentication);
  }

  @Override
  public void logout() {
    SecurityContextHolder.clearContext();
  }


}
