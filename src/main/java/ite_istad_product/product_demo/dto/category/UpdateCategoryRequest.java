package ite_istad_product.product_demo.dto.category;

public record UpdateCategoryRequest(
        String name,
        String description,
        Boolean isActive
) {
}