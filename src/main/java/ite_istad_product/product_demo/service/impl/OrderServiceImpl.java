package ite_istad_product.product_demo.service.impl;


import ite_istad_product.product_demo.dto.order.OrderRequest;
import ite_istad_product.product_demo.dto.order.OrderResponse;
import ite_istad_product.product_demo.entity.Order;
import ite_istad_product.product_demo.entity.OrderLine;
import ite_istad_product.product_demo.mapper.OrderMapper;
import ite_istad_product.product_demo.repository.OrderRepository;
import ite_istad_product.product_demo.repository.ProductRepository;
import ite_istad_product.product_demo.repository.UserRepository;
import ite_istad_product.product_demo.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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

        var customer = userRepository.findById(orderRequest.customerId())
                .orElseThrow(()->new NoSuchElementException("Customer with id ="+orderRequest.customerId()+" not found!"));
        order.setCustomer(customer);

        // 2. validation on orderItems
        var orderLines = orderRequest.items().stream()
                .map(item-> {
                            // validate on product id
                            var product = productRepository.findById(item.productId())
                                    .orElseThrow(()-> new NoSuchElementException("Product with id ="+item.productId()+" not found!"));
                            // validation if the product available  or not
                            if(!product.getIsavailable())
                                throw new NoSuchElementException("Product with id ="+item.productId()+" not available!");
                            // update qty
                            // stock - order qty
                            // Update product in stock
                            product.setQuantity(product.getQuantity()- item.qty());
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
        Order savedOrder = orderRepository.save(order);
        // subTotal
        // total
        // ....

        return null ;
    }

    @Override
    public List<OrderResponse> getAllOrders() {
        return List.of();
    }

    @Override
    public List<OrderResponse> getAllOrdersByCustomerId(Integer customerId) {
        return List.of();
    }
}