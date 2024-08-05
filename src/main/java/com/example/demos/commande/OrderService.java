package com.example.demos.commande;


import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;

    public Integer save(OrderRequest request) {
        Commande_Order order = orderMapper.toOrder(request);
        Commande_Order savedOrder = orderRepository.save(order);
        return savedOrder.getId();
    }

    public OrderResponse findById(Integer orderId) {
        return orderRepository.findById(orderId)
                .map(orderMapper::toOrderResponse)
                .orElseThrow(() -> new EntityNotFoundException("No Order found with the Id :: " + orderId));
    }

    public List<OrderResponse> findAllOrders() {
        return orderRepository.findAll().stream()
                .map(orderMapper::toOrderResponse)
                .collect(Collectors.toList());
    }

    public Integer updateOrder(OrderRequest request, Integer orderId) {
        Commande_Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new EntityNotFoundException("Order not found with ID: " + orderId));
        orderMapper.updateOrder(order, request);
        Commande_Order updatedOrder = orderRepository.save(order);
        return updatedOrder.getId();
    }

    public void deleteOrder(Integer orderId) {
        Commande_Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new EntityNotFoundException("Order not found with ID: " + orderId));
        orderRepository.delete(order);
    }
}
