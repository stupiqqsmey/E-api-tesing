package ite_istad_product.product_demo.mapper;


import ite_istad_product.product_demo.dto.CategoryRequest;
import ite_istad_product.product_demo.dto.CategoryResponse;
import ite_istad_product.product_demo.entity.Category;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    CategoryResponse toResponse(Category category);
    Category toEntity(CategoryRequest request);
}
