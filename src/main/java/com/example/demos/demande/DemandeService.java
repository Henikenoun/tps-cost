package com.example.demos.demande;



import com.example.demos.product.Product;
import com.example.demos.product.ProductRepository;
import com.example.demos.project.Project;
import com.example.demos.project.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class DemandeService {

    @Autowired
    private DemandeRepository demandeRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProjectRepository projectRepository;

    public Demande createDemande(Integer projectId, List<Integer> productIds, Integer quantity) {
        Project project = projectRepository.findById(projectId).orElseThrow(() -> new IllegalArgumentException("Project not found"));
        List<Product> products = productRepository.findAllById(productIds);

        Demande demande = Demande.builder()
                .project(project)
                .products(products)
                .quantity(quantity)
                .entry_date(LocalDate.now())
                .status("en attente")
                .build();

        return demandeRepository.save(demande);
    }

    public Demande approveDemande(Integer demandeId) {
        Demande demande = demandeRepository.findById(demandeId).orElseThrow(() -> new IllegalArgumentException("Demande not found"));
        demande.setStatus("approuvé");
        return demandeRepository.save(demande);
    }

    public Demande rejectDemande(Integer demandeId) {
        Demande demande = demandeRepository.findById(demandeId).orElseThrow(() -> new IllegalArgumentException("Demande not found"));
        demande.setStatus("refusé");
        return demandeRepository.save(demande);
    }
}
