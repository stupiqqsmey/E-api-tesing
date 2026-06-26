package ite_istad_product.product_demo.dto.product;
public record UpdateProductRequest(
        String name,
        String description,
        Double price
){
}
