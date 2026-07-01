package ite_istad_product.product_demo.dto.user;

public record UserResponse(
        Integer id,
        String email,
        String url,
        String bio
) {
}
