package ite_istad_product.product_demo.service;

import ite_istad_product.product_demo.dto.user.CreateUserRequest;
import ite_istad_product.product_demo.dto.user.UserResponse;

import java.util.List;

public interface UserService {
    UserResponse createUser(CreateUserRequest userRequest);
    List<UserResponse> getAllUsers();
}
