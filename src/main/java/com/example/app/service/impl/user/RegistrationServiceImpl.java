package com.example.app.service.impl.user;

import com.example.app.config.authorization.TokenGenerator;
import com.example.app.exception.PasswordUpdateException;
import com.example.app.exception.UserNotFoundException;
import com.example.app.mapper.UserMapper;
import com.example.app.model.User;
import com.example.app.model.auth.TokenInfo;
import com.example.app.model.auth.UpdatePasswordData;
import com.example.app.model.auth.UserData;
import com.example.app.model.exception.ValidationExceptionData;
import com.example.app.repository.UserRepository;
import com.example.app.service.RegistrationService;
import com.example.app.service.UserSecurityContextService;
import java.util.Collections;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class RegistrationServiceImpl implements RegistrationService {


  private final UserDetailsManager userDetailsManager;

  private final UserMapper userMapper;

  private final UserRepository userRepository;

  private final TokenGenerator tokenGenerator;

  private final PasswordEncoder passwordEncoder;

  private final UserSecurityContextService userSecurityContextService;


  @Override
  public TokenInfo registerUser(UserData userData) {
    User userDetails = userMapper.map(userData);
    userDetailsManager.createUser(userDetails);

    Authentication authentication = UsernamePasswordAuthenticationToken
        .authenticated(userDetails, userData.getPassword(), Collections.EMPTY_LIST);

    return tokenGenerator.createToken(authentication);
  }

  @Transactional
  @Override
  public UpdatePasswordData updatePassword(UpdatePasswordData updatePasswordData) {
    final UUID currentUserId = userSecurityContextService.getCurrentUserId();
    final User user = userRepository.findById(currentUserId)
        .orElseThrow(() -> new UserNotFoundException("Id", currentUserId));

    final String oldPassword = updatePasswordData.getOldPassword();
    final String newPassword = updatePasswordData.getNewPassword();

    if (!passwordEncoder.matches(oldPassword, user.getPassword())) {
      throw new PasswordUpdateException(ValidationExceptionData.builder()
          .errorMessage("Wrong password").build());
    }

    user.setPassword(passwordEncoder.encode(newPassword));

    return updatePasswordData;
  }
}
