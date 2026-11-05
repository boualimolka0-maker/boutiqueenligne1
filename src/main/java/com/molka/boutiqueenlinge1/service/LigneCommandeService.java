
package com.molka.boutiqueenlinge1.service;

import java.util.List;
import org.springframework.stereotype.Service;

import com.molka.boutiqueenlinge1.model.LigneCommande;
import com.molka.boutiqueenlinge1.repository.LigneCommandeRepository;

@Service
public class LigneCommandeService {

    private final LigneCommandeRepository repository;

    public LigneCommandeService(LigneCommandeRepository repository) {
        this.repository = repository;
    }

    public List<LigneCommande> getAllLignes() {
        return repository.findAll();
    }

    public LigneCommande getById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public LigneCommande create(LigneCommande ligne) {
        return repository.save(ligne);
    }

    public LigneCommande update(Long id, LigneCommande nouvelleLigne) {
        return repository.findById(id).map(ligne -> {
            ligne.setQuantite(nouvelleLigne.getQuantite());
            ligne.setPrixUnitaire(nouvelleLigne.getPrixUnitaire());
            ligne.setCommande(nouvelleLigne.getCommande());
            ligne.setProduct(nouvelleLigne.getProduct());
            return repository.save(ligne);
        }).orElse(null);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
