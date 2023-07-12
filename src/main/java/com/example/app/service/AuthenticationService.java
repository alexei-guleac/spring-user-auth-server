package com.example.app.service;

import com.example.app.model.auth.LoginData;
import com.example.app.model.auth.TokenInfo;


public interface AuthenticationService {

  TokenInfo loginUser(LoginData loginData);

  TokenInfo getToken(TokenInfo tokenInfo);

  void logout();
}
