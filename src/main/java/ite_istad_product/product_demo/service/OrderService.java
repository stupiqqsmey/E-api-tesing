package ite_istad_product.product_demo.service;


import ite_istad_product.product_demo.dto.order.OrderRequest;
import ite_istad_product.product_demo.dto.order.OrderResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface OrderService {
    OrderResponse createOrder(OrderRequest orderRequest);
    List<OrderResponse> getAllOrders();
    List<OrderResponse> getAllOrdersByCustomerId(Integer customerId);


}
