package ite_istad_product.product_demo.dto;

public record ProductResponse (
        Integer id,
        String name,
        String description,
        Double price
){
}
