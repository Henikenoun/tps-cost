package com.example.demos.commande;


import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderItemResponse {
    private Integer productId;
    private Integer quantity;
}

