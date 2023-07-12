package com.example.app.service.impl.user;


import com.example.app.mapper.RoleMapper;
import com.example.app.model.Role;
import com.example.app.model.auth.RoleData;
import com.example.app.model.enums.RoleEnum;
import com.example.app.repository.RoleRepository;
import com.example.app.service.RoleService;
import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

  private final RoleMapper roleMapper;

  private final String ROLE_NOT_FOUND_BY_ID = "Role with id : %d was not found.";
  private final String ROLE_NOT_FOUND_BY_NAME = "Role with name : %d was not found.";

  @Autowired
  private RoleRepository roleRepository;

  @Override
  public Role findById(long id) {
    return roleRepository.findById(id)
        .orElseThrow(() -> new EntityNotFoundException(ROLE_NOT_FOUND_BY_ID));
  }

  @Override
  public Role findByName(RoleEnum eRole) {
    return roleRepository.findByRoleName(eRole.name())
        .orElseThrow(() -> new EntityNotFoundException(ROLE_NOT_FOUND_BY_NAME));
  }

  @Override
  public RoleData save(RoleData roleDto) {
    Role role = roleMapper.map(roleDto);
    return roleMapper.map(roleRepository.save(role));
  }

  @Override
  public List<Role> findAll() {
    return roleRepository.findAll();
  }
}
