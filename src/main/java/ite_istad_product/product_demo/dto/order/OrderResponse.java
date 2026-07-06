package ite_istad_product.product_demo.dto.order;

import ite_istad_product.product_demo.entity.OrderStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public record OrderResponse(
        UUID id,
        Integer customerId,
        String customerName,
        OrderStatus status ,
        BigDecimal subTotal,
        BigDecimal discount,
        BigDecimal total ,
        LocalDateTime orderedAt,
        List<OrderItemResponse> items
) {
}
