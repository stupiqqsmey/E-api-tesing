package ite_istad_product.product_demo.dto.category;

import jakarta.validation.constraints.Size;
import lombok.Builder;

@Builder
public record CategoryRequest(
   @Size(min = 1,max = 100)
   String name,
   @Size(min = 1, max = 255)
   String description,
   String icon,
   Integer parentCategoryId ///  can = null
) {
}