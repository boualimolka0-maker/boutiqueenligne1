package com.molka.boutiqueenlinge1.controller;

import java.util.List;
import org.springframework.web.bind.annotation.*;

import com.molka.boutiqueenlinge1.model.Paiement;
import com.molka.boutiqueenlinge1.repository.PaiementRepository;

@RestController
@RequestMapping("/api/paiements")
public class PaiementController {

    private final PaiementRepository repository;

    public PaiementController(PaiementRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<Paiement> getAll() {
        return repository.findAll();
    }

    @PostMapping
    public Paiement create(@RequestBody Paiement paiement) {
        return repository.save(paiement);
    }

    @GetMapping("/{id}")
    public Paiement getById(@PathVariable Long id) {
        return repository.findById(id).orElse(null);
    }

    @PutMapping("/{id}")
    public Paiement update(
            @PathVariable Long id,
            @RequestBody Paiement nouveauPaiement) {

        return repository.findById(id).map(paiement -> {
            paiement.setDatePaiement(nouveauPaiement.getDatePaiement());
            paiement.setMontant(nouveauPaiement.getMontant());
            paiement.setModePaiement(nouveauPaiement.getModePaiement());
            paiement.setStatut(nouveauPaiement.getStatut());
            paiement.setCommande(nouveauPaiement.getCommande());
            return repository.save(paiement);
        }).orElse(null);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        repository.deleteById(id);
    }
}