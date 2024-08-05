package com.example.demos.product;

import com.example.demos.demande.Demande;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Product {
    @Id
    @GeneratedValue
    private Integer id;
    @Column(nullable = false, unique = true)
    private String name;
    @Column(nullable = false)
    private String type;
    @Column(nullable = false)
    private Integer unit_price;
    @Column(nullable = false)
    private String unity;

    @ManyToMany(mappedBy = "products")
    private List<Demande> demandes ;

}
