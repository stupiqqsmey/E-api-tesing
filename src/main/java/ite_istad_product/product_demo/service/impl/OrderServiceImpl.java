package ite_istad_product.product_demo.service.impl;


import ite_istad_product.product_demo.dto.order.OrderRequest;
import ite_istad_product.product_demo.dto.order.OrderResponse;
import ite_istad_product.product_demo.entity.Order;
import ite_istad_product.product_demo.entity.OrderLine;
import ite_istad_product.product_demo.entity.OrderStatus;
import ite_istad_product.product_demo.mapper.OrderMapper;
import ite_istad_product.product_demo.repository.OrderRepository;
import ite_istad_product.product_demo.repository.ProductRepository;
import ite_istad_product.product_demo.repository.UserRepository;
import ite_istad_product.product_demo.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class  OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final OrderMapper orderMapper;

    public OrderResponse createOrder(OrderRequest orderRequest) {
        Order order = orderMapper.toOrderEntity(orderRequest);
        order.setOrderedAt(LocalDateTime.now());
        order.setStatus(OrderStatus.PENDING);

        /// 1. validation on customer existence
        var customer = userRepository.findById(orderRequest.customerId())
                .orElseThrow(() -> new NoSuchElementException("Customer with id =" + orderRequest.customerId() + " not found!"));
        order.setCustomer(customer);

        // 2. validation on orderItems
        var orderLines = orderRequest.items().stream()
                .map(item -> {
                            // validate on product id
                            var product = productRepository.findById(item.productId())
                                    .orElseThrow(() -> new NoSuchElementException("Product with id =" + item.productId() + " not found!"));
                            // validation if the product available  or not
                            if (!product.getIsavailable())
                                throw new NoSuchElementException("Product with id =" + item.productId() + " not available!");
                            // update qty
                            // stock - order qty
                            // Update product in stock
                            product.setQuantity(product.getQuantity() - item.qty());
                            var orderLine = new OrderLine();
                            orderLine.setProduct(product);
                            orderLine.setUnitPrice(product.getPrice());
                            orderLine.setQty(item.qty());
                            orderLine.setOrder(order);
                            return orderLine;
                        }

                ).toList();
        // save all the orderItem inside our order
        order.setItems(orderLines);

        BigDecimal discount = orderRequest.discount() == null ?
                BigDecimal.ZERO :
                orderRequest.discount();
        // product1 5$    10units
        // product2 10$   10units
        // 10x5 = 50
        // 10x10 = 100
        BigDecimal subTotal = orderLines.stream()
                // qty x unitPrice
                .map(line -> line.getUnitPrice().multiply(BigDecimal.valueOf(line.getQty())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);

//        BigDecimal total = subTotal.subtract(discount);

        Order savedOrder = orderRepository.save(order);
     /*   return new OrderResponse(
                savedOrder.getId(),
                customer.getId(),
                customer.getEmail(),
                savedOrder.getStatus(),
                subTotal,
                discount,
                total,
                savedOrder.getOrderedAt(),
                null
        ); */
        return orderMapper.toOrderResponse(savedOrder);
    }

    @Override
    public List<OrderResponse> getAllOrders() {
        return orderRepository.findAll()
                .stream().map(orderMapper::toOrderResponse)
                .toList();
    }

    @Override
    public List<OrderResponse> getAllOrdersByCustomerId(Integer customerId) {
        return List.of();
    }
}