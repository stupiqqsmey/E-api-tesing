package ite_istad_product.product_demo.dto.product;

import ite_istad_product.product_demo.dto.category.CategoryResponse;
import ite_istad_product.product_demo.dto.tag.TagResponse;
import ite_istad_product.product_demo.entity.Category;

import java.math.BigDecimal;
import java.util.Set;

public record ProductResponse (
        Integer id,
        String name,
        String description,
        BigDecimal price,
        String slug,
        String thumbnail,
        Integer qty ,
        CategoryResponse category,

        /// set tags
        Set<TagResponse> tags
){
}
