package com.example.demos.demande;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/demandes")
public class DemandeController {

    @Autowired
    private DemandeService demandeService;

    @PostMapping("/create")
    public ResponseEntity<Demande> createDemande(@RequestParam Integer projectId,
                                                 @RequestParam List<Integer> productIds,
                                                 @RequestParam Integer quantity) {
        Demande demande = demandeService.createDemande(projectId, productIds, quantity);
        return ResponseEntity.ok(demande);
    }

    @PostMapping("/approve/{id}")
    public ResponseEntity<Demande> approveDemande(@PathVariable Integer id) {
        Demande demande = demandeService.approveDemande(id);
        return ResponseEntity.ok(demande);
    }

    @PostMapping("/reject/{id}")
    public ResponseEntity<Demande> rejectDemande(@PathVariable Integer id) {
        Demande demande = demandeService.rejectDemande(id);
        return ResponseEntity.ok(demande);
    }
}
