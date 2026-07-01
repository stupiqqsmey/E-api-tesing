package ite_istad_product.product_demo.dto.order;

public record OrderItemRequest(
        Integer productId,
        Integer qty
) {
}
