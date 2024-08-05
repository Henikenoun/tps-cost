package com.example.demos.demande;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface DemandeRepository extends JpaRepository<Demande, Integer> {
    List<Demande> findByStatus(String status);
}
