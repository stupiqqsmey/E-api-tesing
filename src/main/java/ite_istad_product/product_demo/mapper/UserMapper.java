package ite_istad_product.product_demo.mapper;

import ite_istad_product.product_demo.dto.user.CreateUserRequest;
import ite_istad_product.product_demo.dto.user.UserResponse;
import ite_istad_product.product_demo.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "url", source = "profile.url")
    @Mapping(target = "bio", source = "profile.bio")
    UserResponse toResponse(User user);

    @Mapping(target = "profile.url", source = "url")
    @Mapping(target = "profile.bio", source = "bio")
    User toEntity(CreateUserRequest request);
}