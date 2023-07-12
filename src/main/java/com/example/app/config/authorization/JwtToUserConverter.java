package com.example.app.config.authorization;

import com.example.app.model.User;
import java.util.Collections;
import java.util.UUID;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Component;

@Component
public class JwtToUserConverter implements Converter<Jwt, UsernamePasswordAuthenticationToken> {

  @Override
  public UsernamePasswordAuthenticationToken convert(Jwt jwt) {
    User user = new User();
    user.setId(UUID.fromString(jwt.getSubject()));
    return new UsernamePasswordAuthenticationToken(user, jwt, Collections.EMPTY_LIST);
  }
}