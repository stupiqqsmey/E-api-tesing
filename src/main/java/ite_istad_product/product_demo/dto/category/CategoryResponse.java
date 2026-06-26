package ite_istad_product.product_demo.dto.category;

import lombok.Builder;

import java.util.List;

@Builder
public record CategoryResponse(
        Integer id,
        String name,
        String description,
        List<CategoryResponse> subcategory
) {
}