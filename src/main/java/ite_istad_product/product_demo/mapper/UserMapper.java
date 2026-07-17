package ite_istad_product.product_demo.mapper;

import ite_istad_product.product_demo.dto.auth.RegisterRequest;
import ite_istad_product.product_demo.dto.auth.RegisterResponse;
import ite_istad_product.product_demo.dto.user.CreateUserRequest;
import ite_istad_product.product_demo.dto.user.UserResponse;
import ite_istad_product.product_demo.entity.User;
import org.keycloak.representations.idm.UserRepresentation;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

    // Entity -> UserResponse
    @Mapping(target = "url", source = "profile.url")
    @Mapping(target = "bio", source = "profile.bio")
    UserResponse toResponse(User user);

    // CreateUserRequest -> Entity
    @Mapping(target = "profile.url", source = "url")
    @Mapping(target = "profile.bio", source = "bio")
    User toEntity(CreateUserRequest request);

    // Keycloak User -> RegisterResponse
    RegisterResponse toRegisterResponse(UserRepresentation user);

    // Entity -> RegisterResponse
    @Mapping(target = "firstName", source = "profile.firstName")
    @Mapping(target = "lastName", source = "profile.lastName")
    @Mapping(target = "biography", source = "profile.bio")
    @Mapping(target = "gender", source = "profile.gender")
    RegisterResponse toRegisterResponse(User user);
}