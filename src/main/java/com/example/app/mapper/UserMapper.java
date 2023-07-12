package com.example.app.mapper;

import com.example.app.model.User;
import com.example.app.model.auth.UserData;
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
public interface UserMapper {

  @Mappings({
      @Mapping(target = "mail", source = "mail"),
      @Mapping(target = "password", source = "password"),
      @Mapping(target = "firstName", source = "firstName"),
      @Mapping(target = "lastName", source = "lastName"),
      @Mapping(target = "middleName", source = "middleName"),
      @Mapping(target = "birthday", source = "birthday"),
  })
  @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
  User map(UserData userData);

  @Mappings({
      @Mapping(target = "id", source = "id"),
      @Mapping(target = "mail", source = "mail"),
      @Mapping(target = "password", source = "password"),
      @Mapping(target = "firstName", source = "firstName"),
      @Mapping(target = "lastName", source = "lastName"),
      @Mapping(target = "middleName", source = "middleName"),
      @Mapping(target = "birthday", source = "birthday"),
  })
  @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
  UserData map(User user);

//  @Named("mapID")
//  default List<ProductOverview> mapProducts(ProductType value){
//    List<ProductOverview> products = new ArrayList<>();
//
//    //add your custom mapping implementation
//
//    return products;
//  }

}
