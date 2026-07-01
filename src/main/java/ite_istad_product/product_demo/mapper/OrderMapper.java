package ite_istad_product.product_demo.mapper;


import ite_istad_product.product_demo.dto.order.OrderRequest;
import ite_istad_product.product_demo.dto.order.OrderResponse;
import ite_istad_product.product_demo.entity.Order;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrderMapper {
    OrderResponse toOrderResponse(Order order);
    Order toOrderEntity(OrderRequest order);
}
