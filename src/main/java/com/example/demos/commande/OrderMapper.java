package com.example.demos.commande;

import com.example.demos.product.Product;
import org.springframework.stereotype.Service;

@Service
public class OrderMapper {

    public Commande_Order toOrder(OrderRequest request) {
        Commande_Order order = new Commande_Order();
        order.setOrderDate(request.getOrderDate());
        order.setUserId(request.getUserId());

        for (OrderItemRequest itemRequest : request.getItems()) {
            OrderItem orderItem = toOrderItem(itemRequest);
            orderItem.setOrder(order);
            order.getItems().add(orderItem);
        }

        return order;
    }

    public OrderResponse toOrderResponse(Commande_Order order) {
        OrderResponse orderResponse = new OrderResponse();
        orderResponse.setId(order.getId());
        orderResponse.setOrderDate(order.getOrderDate());
        orderResponse.setUserId(order.getUserId());

        for (OrderItem orderItem : order.getItems()) {
            OrderItemResponse itemResponse = toOrderItemResponse(orderItem);
            orderResponse.getItems().add(itemResponse);
        }

        return orderResponse;
    }

    public OrderItem toOrderItem(OrderItemRequest request) {
        OrderItem orderItem = new OrderItem();
        Product product = new Product();
        product.setId(request.getProductId());
        orderItem.setProduct(product);
        orderItem.setQuantity(request.getQuantity());
        return orderItem;
    }

    public OrderItemResponse toOrderItemResponse(OrderItem orderItem) {
        OrderItemResponse itemResponse = new OrderItemResponse();
        itemResponse.setProductId(orderItem.getProduct().getId());
        itemResponse.setQuantity(orderItem.getQuantity());
        return itemResponse;
    }

    public void updateOrder(Commande_Order order, OrderRequest request) {
        order.setOrderDate(request.getOrderDate());
        order.setUserId(request.getUserId());

        order.getItems().clear();
        for (OrderItemRequest itemRequest : request.getItems()) {
            OrderItem orderItem = toOrderItem(itemRequest);
            orderItem.setOrder(order);
            order.getItems().add(orderItem);
        }
    }
}
