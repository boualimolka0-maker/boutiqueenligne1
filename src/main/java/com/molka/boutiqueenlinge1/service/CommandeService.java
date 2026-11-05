
package com.molka.boutiqueenlinge1.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.molka.boutiqueenlinge1.model.Commande;
import com.molka.boutiqueenlinge1.repository.CommandeRepository;

@Service
public class CommandeService {

    private final CommandeRepository repository;

    public CommandeService(CommandeRepository repository) {
        this.repository = repository;
    }

    public List<Commande> getAllCommandes() {
        return repository.findAll();
    }

    public Commande getCommandeById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public Commande createCommande(Commande commande) {
        return repository.save(commande);
    }

    public Commande updateCommande(Long id, Commande nouvelleCommande) {
        return repository.findById(id).map(commande -> {
            commande.setDateCommande(nouvelleCommande.getDateCommande());
            commande.setMontantTotal(nouvelleCommande.getMontantTotal());
            commande.setStatut(nouvelleCommande.getStatut());
            commande.setClient(nouvelleCommande.getClient());

            return repository.save(commande);
        }).orElse(null);
    }

    public void deleteCommande(Long id) {
        repository.deleteById(id);
    }
    public List<Commande> getCommandesByStatut(String statut) {
        return repository.findByStatutIgnoreCase(statut);
    }
}
