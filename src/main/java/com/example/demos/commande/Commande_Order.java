package com.example.demos.commande;



import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Commande_Order {
    @Id
    @GeneratedValue
    private Integer id;
    @Column(nullable = false)
    private Date orderDate;
    @Column(nullable = false)
    private Integer userId;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderItem> items;
}
