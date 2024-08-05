package com.example.demos.commande;



import lombok.*;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderResponse {
    private Integer id;
    private Date orderDate;
    private Integer userId;
    private List<OrderItemResponse> items;
}
