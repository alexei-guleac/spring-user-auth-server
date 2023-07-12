package com.example.app.config.authorization;

import com.example.app.config.ProfileConstants;
import com.example.app.model.User;
import com.example.app.model.auth.TokenInfo;
import java.text.MessageFormat;
import java.time.Duration;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Profile;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
@Profile({
    "!" + ProfileConstants.SPRING_PROFILE_DEVELOPMENT
})
public class TokenGenerator {

  private final JwtEncoder accessTokenEncoder;

  @Qualifier("jwtRefreshTokenEncoder")
  private final JwtEncoder refreshTokenEncoder;


  private String createAccessToken(Authentication authentication) {
    User usrDetails = (User) authentication.getPrincipal();
    Instant now = Instant.now();

    String scope = usrDetails.getAuthorities().stream()
        .map(GrantedAuthority::getAuthority)
        .collect(Collectors.joining(" "));

    Map<String, Object> claims = new HashMap<>();
    Map<String, Object> claimsT = new HashMap<>();
    claimsT.put("dsf", "dfgfg");

    JwtClaimsSet claimsSet = JwtClaimsSet.builder()
        .issuer("app")
        .issuedAt(now)
        .expiresAt(now.plus(5, ChronoUnit.MINUTES))
        .subject(usrDetails.getId().toString())
        .claim("scope", scope)
        .claim("dsf", "dfgfg")
        .claim("dssdf", "dfgfg")
        .claim("dft", claimsT)
        .build();

    return accessTokenEncoder.encode(JwtEncoderParameters.from(claimsSet)).getTokenValue();
  }

  private String createRefreshToken(Authentication authentication) {
    User usrDetails = (User) authentication.getPrincipal();
    Instant now = Instant.now();

    String scope = usrDetails.getAuthorities().stream()
        .map(GrantedAuthority::getAuthority)
        .collect(Collectors.joining(" "));

    JwtClaimsSet claimsSet = JwtClaimsSet.builder()
        .issuer("app")
        .issuedAt(now)
        .expiresAt(now.plus(30, ChronoUnit.DAYS))
        .subject(usrDetails.getId().toString())
        .claim("scope", scope)
        .build();

    return refreshTokenEncoder.encode(JwtEncoderParameters.from(claimsSet)).getTokenValue();
  }

  public TokenInfo createToken(Authentication authentication) {
    if (!(authentication.getPrincipal() instanceof User)) {
      throw new BadCredentialsException(
          MessageFormat
              .format("principal {0} is not of User type", authentication.getPrincipal().getClass())
      );
    }
    User usrDetails = (User) authentication.getPrincipal();
    TokenInfo tokenInfo = new TokenInfo();
    tokenInfo.setUserId(usrDetails.getId().toString());
    tokenInfo.setAccessToken(createAccessToken(authentication));

    String refreshToken;
    if (authentication.getCredentials() instanceof Jwt) {
      Jwt jwt = (Jwt) authentication.getCredentials();
      Instant now = Instant.now();
      Instant expiresAt = jwt.getExpiresAt();
      Duration duration = Duration.between(now, expiresAt);
      long daysUntilExpired = duration.toDays();
      if (daysUntilExpired < 7) {
        refreshToken = createRefreshToken(authentication);
      } else {
        refreshToken = jwt.getTokenValue();
      }
    } else {
      refreshToken = createRefreshToken(authentication);
    }
    tokenInfo.setRefreshToken(refreshToken);

    return tokenInfo;
  }
}