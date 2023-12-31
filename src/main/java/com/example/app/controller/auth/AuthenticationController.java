package com.example.app.controller.auth;


import static com.example.app.constants.RequestMappings.AUTH_ROOT;

import com.example.app.config.ProfileConstants;
import com.example.app.controller.api.AuthenticationApi;
import com.example.app.model.auth.LoginData;
import com.example.app.model.auth.TokenInfo;
import com.example.app.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Profile({
    "!" + ProfileConstants.SPRING_PROFILE_DEVELOPMENT
})
@RequestMapping(AUTH_ROOT)
public class AuthenticationController implements AuthenticationApi {

  private final AuthenticationService authenticateService;

  @Override
  public TokenInfo loginUser(LoginData loginData) {
    return authenticateService.loginUser(loginData);
  }

  @Override
  public void logout() {
    authenticateService.logout();
  }

  @Override
  public TokenInfo getToken(TokenInfo tokenInfo) {
    return authenticateService.getToken(tokenInfo);
  }
}
