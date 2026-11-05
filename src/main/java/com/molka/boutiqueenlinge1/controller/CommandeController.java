
package com.molka.boutiqueenlinge1.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.molka.boutiqueenlinge1.model.Commande;
import com.molka.boutiqueenlinge1.service.CommandeService;

@RestController
@RequestMapping("/api/commandes")
public class CommandeController {

    private final CommandeService commandeService;

    public CommandeController(CommandeService commandeService) {
        this.commandeService = commandeService;
    }

    @GetMapping
    public List<Commande> afficherCommandes() {
        return commandeService.getAllCommandes();
    }

    @GetMapping("/{id}")
    public Commande afficherCommandeParId(@PathVariable Long id) {
        return commandeService.getCommandeById(id);
    }

    @PostMapping
    public Commande ajouterCommande(@RequestBody Commande commande) {
        return commandeService.createCommande(commande);
    }

    @PutMapping("/{id}")
    public Commande modifierCommande(
            @PathVariable Long id,
            @RequestBody Commande commande) {
        return commandeService.updateCommande(id, commande);
    }

    @DeleteMapping("/{id}")
    public void supprimerCommande(@PathVariable Long id) {
        commandeService.deleteCommande(id);
    }

@GetMapping("/statut/{statut}")
public List<Commande> rechercherParStatut(
        @PathVariable String statut) {
    return commandeService.getCommandesByStatut(statut);
}

}
