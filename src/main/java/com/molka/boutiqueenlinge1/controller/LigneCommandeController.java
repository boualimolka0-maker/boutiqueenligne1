package com.molka.boutiqueenlinge1.controller;

import java.util.List;
import org.springframework.web.bind.annotation.*;

import com.molka.boutiqueenlinge1.model.LigneCommande;
import com.molka.boutiqueenlinge1.repository.LigneCommandeRepository;

@RestController
@RequestMapping("/api/lignes-commandes")
public class LigneCommandeController {

    private final LigneCommandeRepository repository;

    public LigneCommandeController(LigneCommandeRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<LigneCommande> getAll() {
        return repository.findAll();
    }

    @PostMapping
    public LigneCommande create(@RequestBody LigneCommande ligneCommande) {

        System.out.println("CREATE METHOD REACHED");

        return repository.save(ligneCommande);
    }
    @GetMapping("/{id}")
    public LigneCommande getById(@PathVariable Long id) {
        return repository.findById(id).orElse(null);
    }

    @PutMapping("/{id}")
    public LigneCommande update(
            @PathVariable Long id,
            @RequestBody LigneCommande nouvelleLigne) {

        return repository.findById(id).map(ligne -> {
            ligne.setQuantite(nouvelleLigne.getQuantite());
            ligne.setPrixUnitaire(nouvelleLigne.getPrixUnitaire());
            ligne.setCommande(nouvelleLigne.getCommande());
            ligne.setProduct(nouvelleLigne.getProduct());
            return repository.save(ligne);
        }).orElse(null);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        repository.deleteById(id);
    }
}