package com.example.demos.commande;

import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Commande_Order, Integer> {
}
