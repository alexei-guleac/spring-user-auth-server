package com.example.app.service;

import com.example.app.model.User;
import com.example.app.model.auth.UserData;
import jakarta.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.UUID;


public interface UserSecurityContextService {

  User findById(UUID id);

  UserData currentUser();

  User getCurrentUser();

  UUID getCurrentUserId();

  String getUserPrincipalInfo();

  Map<String, String> getRequestHeaders(HttpServletRequest httpServletRequest);

}
