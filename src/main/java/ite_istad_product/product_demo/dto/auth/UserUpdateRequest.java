package ite_istad_product.product_demo.dto.auth;

import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Builder;

@Builder
public record UserUpdateRequest(
        @Size(max = 50, message = "First name most be required")
        String firstName,
        @Size(max = 50, message = "Last name most be required")
        String lastName,
        @Pattern(regexp = "^(male|female|other)$")
        String gender,
        @Size(max = 30, message = "Biography must be required")
        String biography,
        @Pattern(regexp = "^(https?://.*)?$", message = "Profile URL must be a valid URL")
        String profileUrl
) {
}