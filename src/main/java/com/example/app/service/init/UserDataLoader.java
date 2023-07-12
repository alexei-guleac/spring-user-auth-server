package com.example.app.service.init;

import static com.example.app.model.enums.RoleEnum.ADMIN;
import static com.example.app.model.enums.RoleEnum.USER;
import static org.apache.commons.collections4.CollectionUtils.isEmpty;

import com.example.app.model.Role;
import com.example.app.model.User;
import com.example.app.repository.RoleRepository;
import com.example.app.repository.UserRepository;
import com.example.app.repository.UserRoleRepository;
import jakarta.annotation.PostConstruct;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserDataLoader {

  private final UserRepository userRepository;

  private final RoleRepository roleRepository;

  private final UserRoleRepository userRoleRepository;

  @PostConstruct
  public void addAdminUser() {
    if (isEmpty(userRepository.findAll())) {

      final Role roleAdmin = roleRepository.saveAndFlush(Role.builder()
          .roleName(ADMIN.name())
          .build());
      final Role roleUser = roleRepository.saveAndFlush(Role.builder()
          .roleName(USER.name())
          .build());

      Set<Role> roles = new HashSet<>();
      roles.add(roleAdmin);
      roles.add(roleUser);

      userRepository.save(User.builder()
          .mail("admin@example.com")
          .password("$2a$12$4m1aIANFu9s1HgVoFaLzruF8J/XybGxo9Vs/pgysC9WQL4aFqTKaG")
          .firstName("Admin")
          .lastName("Admin")
          .middleName("Admin")
          .createDateTime(Timestamp.valueOf(LocalDateTime.now()))
          .createUserName("Admin")
          .updateDateTime(Timestamp.valueOf(LocalDateTime.now()))
          .updateUserName("Admin")
          .active(true)
          .birthday(LocalDate.of(1998, 7, 14))
          .roles(roles)
          .build());
    }
  }
}
