package ite_istad_product.product_demo.dto;

public record CategoryResponse(
        Integer id,
        String name,
        String description,
        boolean isActive
) {
}