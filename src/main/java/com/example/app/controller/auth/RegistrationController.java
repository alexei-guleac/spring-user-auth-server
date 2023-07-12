package com.example.app.controller.auth;


import static com.example.app.constants.RequestMappings.AUTH_ROOT;

import com.example.app.config.ProfileConstants;
import com.example.app.controller.api.RegistrationApi;
import com.example.app.model.auth.TokenInfo;
import com.example.app.model.auth.UpdatePasswordData;
import com.example.app.model.auth.UserData;
import com.example.app.service.RegistrationService;
import jakarta.validation.Valid;
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
public class RegistrationController implements RegistrationApi {


  private final RegistrationService registrationService;

  @Override
  public TokenInfo registerUser(UserData signUpData) {
    return registrationService.registerUser(signUpData);
  }

  @Override
  public void updatePassword(@Valid UpdatePasswordData updatePasswordData) {
    registrationService.updatePassword(updatePasswordData);
  }

}
