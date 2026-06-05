package ite_istad_product.product_demo.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CategoryRequest(
        @NotBlank(message = "Name is required")
        String name,
        String description,
        @NotNull(message = "Active status is required")
        Boolean isActive
) {
}