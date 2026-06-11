package ite_istad_product.product_demo.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record ProductRequest(
        @NotBlank(message = "name is  required")
        String name,
        @NotBlank (message = "description is required")
        String description,
        @NotNull(message = "price is required")
        @Positive(message = "price must be positive")
        double price
) {
}
