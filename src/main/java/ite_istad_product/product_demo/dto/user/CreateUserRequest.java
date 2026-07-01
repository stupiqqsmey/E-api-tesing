package ite_istad_product.product_demo.dto.user;

import lombok.Builder;

@Builder
public record CreateUserRequest(
        String email,
        String password,
        String url,
        String bio
) {
}
