package ite_istad_product.product_demo.dto.tag;

import lombok.Builder;

@Builder
public record TagRequest(
        String name
) {
}
