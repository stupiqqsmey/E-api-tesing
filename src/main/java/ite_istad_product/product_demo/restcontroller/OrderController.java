package ite_istad_product.product_demo.restcontroller;


import ite_istad_product.product_demo.dto.order.OrderRequest;
import ite_istad_product.product_demo.dto.order.OrderResponse;
import ite_istad_product.product_demo.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/orders")
public class OrderController {
    private final OrderService orderService;

    @GetMapping
    public List<OrderResponse> getOrders() {
        return orderService.getAllOrders();
    }
    @PostMapping
    public OrderResponse creatOrder(@RequestBody OrderRequest request){
        return orderService.createOrder(request);
    }
    // get all orders of customer with id
    // typically recide in the customer rest controller
    // api/v1/customers/12/orders
}
