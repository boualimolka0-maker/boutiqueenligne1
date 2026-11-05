package com.molka.boutiqueenlinge1.controller;

import java.util.List;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.molka.boutiqueenlinge1.model.Client;
import com.molka.boutiqueenlinge1.repository.ClientRepository;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
@RestController
@RequestMapping("/api/clients")
public class ClientController {

    @Autowired
    private ClientRepository clientRepository;

    @GetMapping
    public List<Client> afficherClients() {
        return clientRepository.findAll();
    }

    @PostMapping
    public Client ajouterClient(@Valid @RequestBody Client client) {
        return clientRepository.save(client);
    }
    @GetMapping("/{id}")
    public Client afficherClientParId(@PathVariable Long id) {
        return clientRepository.findById(id).orElse(null);
    }

    @PutMapping("/{id}")
    public Client modifierClient(
            @PathVariable Long id,
            @Valid @RequestBody Client nouveauClient) {
        return clientRepository.findById(id).map(client -> {
            client.setNom(nouveauClient.getNom());
            client.setPrenom(nouveauClient.getPrenom());
            client.setEmail(nouveauClient.getEmail());
            client.setTelephone(nouveauClient.getTelephone());

            return clientRepository.save(client);
        }).orElse(null);
    }

    @DeleteMapping("/{id}")
    public void supprimerClient(@PathVariable Long id) {
        clientRepository.deleteById(id);
    }
}