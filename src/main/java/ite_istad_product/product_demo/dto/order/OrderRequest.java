package ite_istad_product.product_demo.dto.order;

import java.math.BigDecimal;
import java.util.List;

public record OrderRequest(
        Integer customerId,
        String address ,
        String remark,
        BigDecimal discount ,
        List<OrderItemRequest> items
) {
}
