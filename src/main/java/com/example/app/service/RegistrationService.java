package com.example.app.service;

import com.example.app.model.auth.TokenInfo;
import com.example.app.model.auth.UserData;


public interface RegistrationService {

  TokenInfo registerUser(UserData signUpData);

}
