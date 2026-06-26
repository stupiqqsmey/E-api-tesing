package ite_istad_product.product_demo.mapper;

import ite_istad_product.product_demo.dto.product.ProductRequest;
import ite_istad_product.product_demo.dto.product.ProductResponse;
import ite_istad_product.product_demo.entity.Product;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    ProductResponse toProductResponse(Product product);
    Product toEntity(ProductRequest productRequest);
}