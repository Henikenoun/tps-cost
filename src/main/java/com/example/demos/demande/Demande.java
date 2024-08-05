package com.example.demos.demande;

import com.example.demos.product.Product;
import com.example.demos.project.Project;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Demande {

    @Id
    @GeneratedValue
    private Integer id;

    private Integer quantity;
    private LocalDate entry_date;
    private LocalDate sort_date;
    private String status;

    @ManyToOne
    @JoinColumn(name = "project_id")
    private Project project;

    @ManyToMany
    private List<Product> products;
}
