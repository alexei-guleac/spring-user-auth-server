package com.example.app;

import com.example.app.config.authorization.TokenGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AuthenticationHelper {

  private final TokenGenerator tokenGenerator;

  private final DaoAuthenticationProvider daoAuthenticationProvider;

  public String authenticate(String email, String password) {
    Authentication authentication = daoAuthenticationProvider.authenticate(
        UsernamePasswordAuthenticationToken
            .unauthenticated(email, password));

    return tokenGenerator.createToken(authentication).getAccessToken();
  }

  public <T> HttpEntity<T> generateHeaders(String token) {
    return generateHeaders(null, token);
  }

  public <T> HttpEntity<T> generateHeaders(T body, String token) {
    var headers = new HttpHeaders();
    headers.add(HttpHeaders.AUTHORIZATION, "Bearer " + token);
    if (body != null) {
      return new HttpEntity<>(body, headers);
    }
    return new HttpEntity<>(headers);
  }
}
