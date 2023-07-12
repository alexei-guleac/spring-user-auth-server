package com.example.app.util;

import com.nimbusds.jwt.SignedJWT;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
@Qualifier("jwtUtils")
public class JwtUtils {

  /**
   * accessToken: the JWT string text.
   **/
  @SneakyThrows
  public String parseJWT(String accessToken) {
    var decodedJWT = SignedJWT.parse(accessToken);
    var header = decodedJWT.getHeader().toString();
    return decodedJWT.getPayload().toString();
  }

  @SneakyThrows
  public String getSubFormJWT(String accessToken) {
    return SignedJWT.parse(accessToken).getJWTClaimsSet().getSubject();
  }

}
