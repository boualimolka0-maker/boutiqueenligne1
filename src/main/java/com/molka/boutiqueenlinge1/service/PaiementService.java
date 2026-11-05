
package com.molka.boutiqueenlinge1.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.molka.boutiqueenlinge1.model.Paiement;
import com.molka.boutiqueenlinge1.repository.PaiementRepository;

@Service
public class PaiementService {

    private final PaiementRepository repository;

    public PaiementService(PaiementRepository repository) {
        this.repository = repository;
    }

    public List<Paiement> getAllPaiements() {
        return repository.findAll();
    }

    public Paiement getPaiementById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public Paiement createPaiement(Paiement paiement) {
        return repository.save(paiement);
    }

    public Paiement updatePaiement(Long id, Paiement nouveauPaiement) {
        return repository.findById(id).map(paiement -> {
            paiement.setDatePaiement(nouveauPaiement.getDatePaiement());
            paiement.setMontant(nouveauPaiement.getMontant());
            paiement.setModePaiement(nouveauPaiement.getModePaiement());
            paiement.setStatut(nouveauPaiement.getStatut());
            paiement.setCommande(nouveauPaiement.getCommande());

            return repository.save(paiement);
        }).orElse(null);
    }

    public void deletePaiement(Long id) {
        repository.deleteById(id);
    }
}
