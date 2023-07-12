package com.example.app.mapper;

import com.example.app.model.Role;
import com.example.app.model.auth.RoleData;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValueMappingStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;


@Mapper(componentModel = "spring",
    nullValueMappingStrategy = NullValueMappingStrategy.RETURN_DEFAULT,
    nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
    nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS
)
public interface RoleMapper {

  @Mappings({
      @Mapping(target = "id", source = "id"),
      @Mapping(target = "roleName", source = "roleName"),
  })
  @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
  Role map(RoleData roleData);

  @Mappings({
      @Mapping(target = "id", source = "id"),
      @Mapping(target = "roleName", source = "roleName"),
  })
  @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
  RoleData map(Role role);

}
