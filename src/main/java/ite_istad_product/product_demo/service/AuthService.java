package ite_istad_product.product_demo.service;

import ite_istad_product.product_demo.dto.auth.RegisterRequest;
import ite_istad_product.product_demo.dto.auth.RegisterResponse;
import ite_istad_product.product_demo.dto.auth.UserUpdateRequest;
import ite_istad_product.product_demo.dto.user.UserResponse;

public interface AuthService {
    // register the new user
    RegisterResponse register(RegisterRequest request );
    UserResponse updateUser(String keycloakID, UserUpdateRequest request);
    void forgotPassword(String email);
}
