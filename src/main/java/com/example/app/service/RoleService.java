package com.example.app.service;


import com.example.app.model.Role;
import com.example.app.model.auth.RoleData;
import com.example.app.model.enums.RoleEnum;
import java.util.List;

public interface RoleService {

  Role findById(long id);

  Role findByName(RoleEnum eRole);

  RoleData save(RoleData role);

  List<Role> findAll();

}
