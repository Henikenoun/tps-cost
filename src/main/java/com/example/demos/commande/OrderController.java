package com.example.demos.commande;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService service;

    @PostMapping
    public ResponseEntity<Integer> saveOrder(
            @Valid @RequestBody OrderRequest request
    ) {
        return ResponseEntity.ok(service.save(request));
    }

    @GetMapping("{order-id}")
    public ResponseEntity<OrderResponse> findOrderById(@PathVariable("order-id") Integer orderId) {
        return ResponseEntity.ok(service.findById(orderId));
    }

    @GetMapping
    public ResponseEntity<List<OrderResponse>> findAllOrders() {
        return ResponseEntity.ok(service.findAllOrders());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Integer> updateOrder(
            @Valid @RequestBody OrderRequest request,
            @PathVariable("id") Integer id
    ) {
        return ResponseEntity.ok(service.updateOrder(request, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteOrder(@PathVariable("id") Integer id) {
        service.deleteOrder(id);
        return ResponseEntity.ok("Order deleted successfully");
    }
}

